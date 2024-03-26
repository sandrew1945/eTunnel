package com.sandrew.etunnel.handler;

import com.sandrew.etunnel.protpcol.ETunnelProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by summer on 2019/9/4.
 */
public class ETunnelProtocolEncoder extends MessageToByteEncoder<ETunnelProtocol>
{
    @Override
    protected void encode(ChannelHandlerContext ctx, ETunnelProtocol msg, ByteBuf out) throws Exception
    {
        out.writeInt(msg.getMagicNumber());
        out.writeByte(msg.getVersion());
        out.writeByte(msg.getAlgorithm());
        out.writeByte(msg.getCommand());
        out.writeInt(msg.getLength());
        if (msg.getContent().length > 0)
        {
            out.writeBytes(msg.getContent());
        }
    }
}
