package com.sandrew.etunnel.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.stream.ChunkedStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @ClassName ChunkedFileUploadHandler
 * @Description
 * @Author summer
 * @Date 2024/4/16 10:43
 **/
public class ChunkedFileUploadHandler extends ChannelInboundHandlerAdapter
{
    private static Logger log = LoggerFactory.getLogger(ChunkedFileUploadHandler.class);



    public void sendData(Channel channel, File largeFile)
    {
        try
        {
            ChannelPromise channelPromise = channel.writeAndFlush(new ChunkedStream(new FileInputStream(largeFile))).channel().newPromise();
            channelPromise.await();

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        log.info("Server start to handle file upload.");
        log.debug("fileId : " + msg);
    }


}
