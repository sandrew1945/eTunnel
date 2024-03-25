package com.sandrew.etunnel.server;

import com.sandrew.etunnel.protpcol.Command;
import com.sandrew.etunnel.protpcol.ETunnelProtocol;
import com.sandrew.etunnel.protpcol.UploadRequestPacket;
import com.sandrew.etunnel.protpcol.UploadResponsePacket;
import com.sandrew.etunnel.protpcol.serializer.Serializer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.UUID;

/**
 * Created by summer on 2019/9/5.
 */
public class FileUploadHandler extends SimpleChannelInboundHandler<ETunnelProtocol>
{
    private static Logger log = LoggerFactory.getLogger(FileUploadHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ETunnelProtocol msg) throws Exception
    {
        if (Command.UPLOAD_REQ.getCommand() == msg.getCommand())
        {
            log.info("Server start to handle file upload.");
            Serializer serializer = Serializer.getSerializerByType(msg.getAlgorithm());
            UploadRequestPacket packet = serializer.deserialize(msg.getContent(), UploadRequestPacket.class);
            String fileMD5 = packet.getFileMD5();
            String fileName = packet.getFileName();
            long fileSize = packet.getFileSize();
            String fileSuffix = packet.getFileSuffix();
            File uploadFile = packet.getFile();
            log.debug("fileMD5 : " + fileMD5);
            log.debug("fileName : " + fileName);
            log.debug("uploadFile : " + uploadFile);
            // 返回响应信息
            UploadResponsePacket repsonse = new UploadResponsePacket(serializer);
            repsonse.setFileId(UUID.randomUUID().toString());
            repsonse.setFileUrl("");
            ctx.writeAndFlush(new ETunnelProtocol(repsonse));
        }
        else
        {
            ctx.fireChannelRead(msg);
        }

    }
}
