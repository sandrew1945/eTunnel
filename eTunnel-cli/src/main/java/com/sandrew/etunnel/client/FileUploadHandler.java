package com.sandrew.etunnel.client;

import com.sandrew.etunnel.handler.CommunicateHandler;
import com.sandrew.etunnel.handler.SyncChannelInboundHandler;
import com.sandrew.etunnel.protpcol.Command;
import com.sandrew.etunnel.protpcol.ETunnelProtocol;
import com.sandrew.etunnel.protpcol.UploadRequestPacket;
import com.sandrew.etunnel.protpcol.UploadResponsePacket;
import com.sandrew.etunnel.protpcol.serializer.Serializer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName FileUploadHandler
 * @Description
 * @Author summer
 * @Date 2024/3/25 15:38
 **/
public class FileUploadHandler extends SyncChannelInboundHandler<UploadResponsePacket> implements CommunicateHandler<UploadRequestPacket, UploadResponsePacket>
{

    private static Logger log = LoggerFactory.getLogger(FileUploadHandler.class);

    @Override
    public FileUploadHandler sendData(Channel channel, UploadRequestPacket packet)
    {
        this.channelPromise = channel.writeAndFlush(new ETunnelProtocol(packet)).channel().newPromise();
        return this;
    }

    @Override
    public UploadResponsePacket receiveData() throws InterruptedException
    {
        channelPromise.await();
        return this.receivePacket;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ETunnelProtocol msg) throws Exception
    {
        if (Command.UPLOAD_RES.getCommand() == msg.getCommand())
        {
            log.info("Server start to handle file upload.");
            Serializer serializer = Serializer.getSerializerByType(msg.getAlgorithm());
            UploadResponsePacket packet = serializer.deserialize(msg.getContent(), UploadResponsePacket.class);
            this.receivePacket = packet;
            this.channelPromise.setSuccess();
            log.debug("fileId : " + packet.getFileId());
            log.debug("fileUrl : " + packet.getFileUrl());
        }
        else
        {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        if (null != cause)
        {
            log.error(cause.getMessage(), cause.getStackTrace());
        }
    }
}
