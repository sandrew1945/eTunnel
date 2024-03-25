package com.sandrew.etunnel.client;

import com.sandrew.etunnel.protpcol.Command;
import com.sandrew.etunnel.protpcol.ETunnelProtocol;
import com.sandrew.etunnel.protpcol.UploadResponsePacket;
import com.sandrew.etunnel.protpcol.serializer.Serializer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName FileUploadHandler
 * @Description
 * @Author summer
 * @Date 2024/3/25 15:38
 **/
public class FileUploadHandler extends SimpleChannelInboundHandler<ETunnelProtocol>
{

    private static Logger log = LoggerFactory.getLogger(FileUploadHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ETunnelProtocol msg) throws Exception
    {
        if (Command.UPLOAD_RES.getCommand() == msg.getCommand())
        {
            log.info("Server start to handle file upload.");
            Serializer serializer = Serializer.getSerializerByType(msg.getAlgorithm());
            UploadResponsePacket packet = serializer.deserialize(msg.getContent(), UploadResponsePacket.class);
            String fileId = packet.getFileId();
            String fileUrl = packet.getFileUrl();
            log.debug("fileId : " + fileId);
            log.debug("fileUrl : " + fileUrl);
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
