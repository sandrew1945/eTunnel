package com.sandrew.etunnel.protpcol;

import com.sandrew.etunnel.protpcol.serializer.Serializer;
import com.sandrew.etunnel.protpcol.serializer.SerializerType;

/**
 * @ClassName UploadResponsePacket
 * @Description
 * @Author summer
 * @Date 2024/3/22 10:15
 **/
public class UploadResponsePacket extends Packet
{
    private String fileId;

    private String fileUrl;

    public UploadResponsePacket(Serializer aSerializer)
    {
        this.serializer = aSerializer;
    }


    @Override
    public SerializerType getAlgorithm()
    {
        return this.serializer.getSerializerAlgorithm();
    }

    @Override
    public Command getCommand()
    {
        return Command.UPLOAD_RES;
    }

    public String getFileId()
    {
        return fileId;
    }

    public void setFileId(String fileId)
    {
        this.fileId = fileId;
    }

    public String getFileUrl()
    {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl)
    {
        this.fileUrl = fileUrl;
    }
}
