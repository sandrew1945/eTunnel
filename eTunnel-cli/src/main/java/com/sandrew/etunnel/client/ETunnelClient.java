package com.sandrew.etunnel.client;

import com.sandrew.etunnel.handler.ETunnelProtocolDecoder;
import com.sandrew.etunnel.handler.ETunnelProtocolEncoder;
import com.sandrew.etunnel.protpcol.UploadRequestPacket;
import com.sandrew.etunnel.protpcol.UploadResponsePacket;
import com.sandrew.etunnel.protpcol.serializer.HessianSerializer;
import com.sandrew.etunnel.protpcol.serializer.Serializer;
import com.sandrew.etunnel.util.FileUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ETunnelClient
 * @Description
 * @Author summer
 * @Date 2024/3/25 15:45
 **/
public class ETunnelClient
{

    private static Logger log = LoggerFactory.getLogger(FileUploadHandler.class);
    private static EventLoopGroup workerGroup = new NioEventLoopGroup();

    private FileUploadHandler fileUploadHandler = new FileUploadHandler();

    private Channel endpoint = null;

    public void connect(String host, int port)
    {
        try
        {
            Bootstrap client = new Bootstrap();

            client.group(this.workerGroup).channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>()
                    {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception
                        {
                            ch.pipeline().addLast(new ETunnelProtocolDecoder())
                                    .addLast(new ETunnelProtocolEncoder())
                                    .addLast(fileUploadHandler);
                        }
                    });
            this.endpoint = doConnect(client, host, port, 5);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
    }

    private Channel doConnect(final Bootstrap client, final String host, final int port, final int retry)
    {
        Channel channel = null;
        try
        {
            ChannelFuture connectFuture = client.connect(host, port);
            if (connectFuture.sync().isSuccess())
            {
                channel = connectFuture.channel();
            }
            else
            {
                if (retry <= 0)
                {
                    log.error("Clent connect failed and try more times.");
                }
                else
                {
                    log.error("Client connect failed, Try it again!");
                    client.config().group().schedule(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            doConnect(client, host, port, retry - 1);
                        }
                    }, 5, TimeUnit.SECONDS);
                }
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        return channel;
    }

    public String fileUpload(File file)
    {
        try
        {
            return fileUpload(file, new HessianSerializer());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public String fileUpload(File file, Serializer serializer)
    {
        try
        {
            UploadRequestPacket packet = new UploadRequestPacket(serializer);
            packet.setFileName(file.getName());
            packet.setFile(FileUtil.file2Bytes(file));
            packet.setFileMD5(FileUtil.getFileMD5(file));
            packet.setFileSuffix(FileUtil.getFileExtension(file));
            packet.setFileSize(file.length());
            //        this.endpoint.writeAndFlush(new ETunnelProtocol(packet));
//            UploadResponsePacket uploadResponsePacket = fileUploadHandler.sendData(this.endpoint, packet).receiveData();
            UploadResponsePacket uploadResponsePacket = fileUploadHandler.sendAndReceive(this.endpoint, packet);
            return uploadResponsePacket.getFileId();
        }
        catch (InterruptedException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
