package com.sandrew.etunnel.handler;

import com.sandrew.etunnel.protpcol.ETunnelProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName SyncChannelInboundHandler
 * @Description
 * @Author summer
 * @Date 2024/3/26 14:28
 **/
public abstract class SyncChannelInboundHandler<R> extends SimpleChannelInboundHandler<ETunnelProtocol>
{
    protected ChannelPromise channelPromise;

    protected R receivePacket;


    protected abstract void channelRead0(ChannelHandlerContext ctx, ETunnelProtocol msg) throws Exception;
}
