package com.sandrew.etunnel.client;

import com.sandrew.etunnel.codec.ETunnelProtocolDecoder;
import com.sandrew.etunnel.codec.ETunnelProtocolEncoder;
import com.sandrew.etunnel.protpcol.ETunnelProtocol;
import com.sandrew.etunnel.protpcol.UploadRequestPacket;
import com.sandrew.etunnel.protpcol.serializer.JavaSerializer;
import com.sandrew.etunnel.util.FileUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
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

    public void run(String host, int port)
    {
        Channel channel = null;
        try
        {
            Bootstrap client = new Bootstrap();

            client.group(this.workerGroup).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true)
                    //                    .attr(Attributes.USER_NAME, userName)
                    //                    .attr(Attributes.PASSWORD, password)
                    .handler(new ChannelInitializer<SocketChannel>()
                    {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception
                        {
                            ch.pipeline().addLast(new ETunnelProtocolDecoder())
                                    .addLast(new ETunnelProtocolEncoder())
                                    .addLast(new FileUploadHandler());
                        }
                    });
            connect(client, host, port, 5);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void connect(final Bootstrap client, final String host, final int port, final int retry)
    {
        try
        {
            client.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>()
            {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception
                {
                    if (future.isSuccess())
                    {
                        System.out.println("Client connect successful at port:" + port);
                        // 启动控制台输入线程
//                        future.wait();
                        startConsoleInput(((ChannelFuture)future).channel());
                    }
                    else if (retry <= 0)
                    {
                        System.err.println("Clent connect failed and try more times.");
                    }
                    else
                    {
                        System.err.println("Client connect failed, Try it again!");
                        client.config().group().schedule(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                connect(client, host, port, retry - 1);
                            }
                        }, 5, TimeUnit.SECONDS);

                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void startConsoleInput(Channel channel)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (!Thread.interrupted())
                {

                    File uploadFile = new File("/Users/summer/Desktop/新建文本文档(1).txt");
                    UploadRequestPacket packet = new UploadRequestPacket(new JavaSerializer());
                    packet.setFileName(uploadFile.getName());
                    packet.setFile(uploadFile);
                    packet.setFileMD5(FileUtil.getFileMD5(uploadFile));
                    packet.setFileSuffix("txt");
                    packet.setFileSize(uploadFile.length());
                    channel.writeAndFlush(new ETunnelProtocol(packet));

                }
            }
        }).start();
    }
}
