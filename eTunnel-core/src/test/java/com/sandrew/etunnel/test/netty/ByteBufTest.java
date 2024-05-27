package com.sandrew.etunnel.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * @ClassName FileUtilTest
 * @Description
 * @Author summer
 * @Date 2024/3/21 14:13
 **/
public class ByteBufTest
{
    private static Logger log = LoggerFactory.getLogger(ByteBufTest.class);

    @Test
    @DisplayName("ByteBuf get test")
    public void byteBufGetTest()
    {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        log.info("" + (char) buf.getByte(0));
        int readerIndex = buf.readerIndex();
        int writerIndex = buf.writerIndex();
        buf.setByte(0, (byte) 'B');
        log.info("" + (char) buf.getByte(0));
        Assertions.assertEquals(readerIndex, buf.readerIndex());
        Assertions.assertEquals(writerIndex, buf.writerIndex());

    }

    @Test
    @DisplayName("ByteBuf slice test")
    public void byteBufSliceTest()
    {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        ByteBuf sliced = buf.slice(0, 14);
        log.info("======> " + sliced.toString(utf8));
        buf.setByte(0, (byte) 'J');
        Assertions.assertEquals(buf.getByte(0), sliced.getByte(0));
    }

    @Test
    @DisplayName("ByteBuf readByte test")
    public void byteBufReadByteTest()
    {
        Charset utf8 = Charset.forName("UTF-8");
        ByteBuf buf = Unpooled.copiedBuffer("Netty in Action rocks!", utf8);
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes, 0, bytes.length);
        log.info("bytes length =====> " + bytes.length);
        log.info("bytes to string =====> " + new String(bytes));
        Assertions.assertEquals(new String(bytes), "Netty in Action rocks!");
    }
}
