package com.sandrew.etunnel.server;

import com.sandrew.etunnel.config.Configurations;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName ETunnelServer
 * @Description
 * @Author summer
 * @Date 2024/3/21 15:28
 **/
public class ETunnelHttpServer
{
    private static Logger log = LoggerFactory.getLogger(ETunnelHttpServer.class);

    public ETunnelHttpServer(Configurations configurations)
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
            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>()
                    {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception
                        {
                            socketChannel.pipeline().addLast("decoder", new HttpRequestDecoder())
                                    .addLast("encoder", new HttpResponseEncoder())
                                    .addLast("aggregator", new HttpObjectAggregator(1024 * 1024))
                                    .addLast("handler", new HttpServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            bind(server, this.configurations.getHttpServerPort());
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
                    log.info("ETunnel http server startup succuss at port:" + port);
                }
                else
                {
                    log.info("ETunnel http server startup failed at port:" + port + ", try it again!");
                    bind(server, port + 1);
                }
            }
        });

    }
}
