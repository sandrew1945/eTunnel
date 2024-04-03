package com.sandrew.etunnel.server;

import com.sandrew.etunnel.config.Configurations;
import com.sandrew.etunnel.handler.ETunnelProtocolDecoder;
import com.sandrew.etunnel.handler.ETunnelProtocolEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sandrew.etunnel.util.Attributes.SERVER_CONFIG;

/**
 * @ClassName ETunnelServer
 * @Description
 * @Author summer
 * @Date 2024/3/21 15:28
 **/
public class ETunnelServer
{
    private static Logger log = LoggerFactory.getLogger(FileUploadHandler.class);

    public ETunnelServer(Configurations configurations)
    {
        this.configurations = configurations;
    }

    private Configurations configurations;

    public void run() throws InterruptedException
    {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try
        {
            ServerBootstrap server = new ServerBootstrap();
            // Set the global configuration
            server.childAttr(SERVER_CONFIG, configurations);
            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>()
                    {
                        @Override
                        protected void initChannel(NioSocketChannel socketChannel) throws Exception
                        {
                            socketChannel.pipeline()
                                    .addLast(new ETunnelProtocolDecoder())
                                    .addLast(new ETunnelProtocolEncoder())
                                    .addLast(new FileUploadHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            bind(server, configurations.getServerPort());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }
    }

    private void bind(final ServerBootstrap server, final int port)
    {
        ChannelFuture future = server.bind(port);
        future.addListener(new GenericFutureListener<Future<? super Void>>()
        {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception
            {

                if (future.isSuccess())
                {
                    log.info("ETunnel server startup succuss at port:" + port);
                }
                else
                {
                    log.info("ETunnel server startup failed at port:" + port + ", try it again!");
                    bind(server, port + 1);
                }
            }
        });

    }
}
