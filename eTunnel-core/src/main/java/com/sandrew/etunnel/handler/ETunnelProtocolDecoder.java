package com.sandrew.etunnel.handler;

import com.sandrew.etunnel.protpcol.ETunnelProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * Created by summer on 2019/9/4.
 */
public class ETunnelProtocolDecoder extends ReplayingDecoder<ETunnelProtocolDecoder.State>
{
    private ETunnelProtocol protocol;

    public ETunnelProtocolDecoder()
    {
        super(State.MAGIC);
    }

    public enum State
    {
        MAGIC, VERSION, ALGORITHM, COMMAND, LENGTH, CONTENT
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception
    {
        State state = state();
        switch (state)
        {
            case MAGIC:
            {
                protocol = new ETunnelProtocol();
                int magicNum = in.readInt();
                protocol.setMagicNumber(magicNum);
                checkpoint(State.VERSION);
                break;
            }
            case VERSION:
            {
                byte version = in.readByte();
                protocol.setVersion(version);
                checkpoint(State.ALGORITHM);
                break;
            }
            case ALGORITHM:
            {
                byte algorithm = in.readByte();
                protocol.setAlgorithm(algorithm);
                checkpoint(State.COMMAND);
                break;
            }
            case COMMAND:
            {
                byte command = in.readByte();
                protocol.setCommand(command);
                checkpoint(State.LENGTH);
                break;
            }
            case LENGTH:
            {
                int length = in.readInt();
                protocol.setLength(length);
                checkpoint(State.CONTENT);
                break;
            }
            case CONTENT:
            {
                byte[] bytes = new byte[protocol.getLength()];
                in.readBytes(bytes);
                protocol.setContent(bytes);
                out.add(protocol);
                checkpoint(State.MAGIC);
                break;
            }
            default:
                throw new IllegalStateException("invalid state:" + state);
        }
    }
}
