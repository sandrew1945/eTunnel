package com.sandrew.etunnel.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by summer on 2019/9/5.
 */
public class FileChunkedUploadHandler extends ChannelInboundHandlerAdapter
{
    private static Logger log = LoggerFactory.getLogger(FileChunkedUploadHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
    {
        File file = new File("/Users/summer/Desktop/large-file");//remember to change dest

        if (!file.exists()) {
            file.createNewFile();
        }
        ByteBuf byteBuf = (ByteBuf) msg;
        ByteBuffer byteBuffer = byteBuf.nioBuffer();
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        long length = 0;
        while (byteBuffer.hasRemaining()){

            System.out.println("byteBuffer ----------->" + byteBuffer);
            length = length + byteBuffer.capacity();

            fileChannel.position(file.length());
            fileChannel.write(byteBuffer);
        }
        System.out.println("All data size : " + length);
        byteBuf.release();
//        fileChannel.close();
//        randomAccessFile.close();
        // 返回响应信息

        ctx.writeAndFlush("ok");
    }

}
