package com.sandrew.etunnel.protpcol;

import com.sandrew.etunnel.protpcol.serializer.Serializer;
import com.sandrew.etunnel.protpcol.serializer.SerializerType;

import java.io.File;

/**
 * @ClassName UploadRequestPacket
 * @Description
 * @Author summer
 * @Date 2024/3/21 10:38
 **/
public class UploadRequestPacket extends Packet
{

    private String fileName;

    private File file;

    private String fileMD5;

    private String fileSuffix;

    private long fileSize;

    public UploadRequestPacket()
    {
    }

    public UploadRequestPacket(Serializer aSerializer)
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
        return Command.UPLOAD_REQ;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public String getFileMD5()
    {
        return fileMD5;
    }

    public void setFileMD5(String fileMD5)
    {
        this.fileMD5 = fileMD5;
    }

    public String getFileSuffix()
    {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix)
    {
        this.fileSuffix = fileSuffix;
    }

    public long getFileSize()
    {
        return fileSize;
    }

    public void setFileSize(long fileSize)
    {
        this.fileSize = fileSize;
    }
}
