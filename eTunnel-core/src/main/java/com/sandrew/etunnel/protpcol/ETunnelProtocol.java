package com.sandrew.etunnel.protpcol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * Created by summer on 2019/9/3.
 */
public class ETunnelProtocol
{

    /**
     *  协议验证码
     */
    private int magicNumber = 0x123f567f;

    /**
     *  协议版本
     */
    private byte version = 1;

    /**
     *  序列化算法
     */
    private byte algorithm;

    /**
     *  指令
     */
    private byte command;

    /**
     *  数据长度
     */
    private int length;

    /**
     *  数据内容
     */
    private byte[] content;


    public ETunnelProtocol() {}


    public ETunnelProtocol(Packet packet)
    {
        byte[] bytes = packet.serialize();
        this.algorithm = packet.getAlgorithm().getType();
        this.command = packet.getCommand().getCommand();
        this.length = bytes.length;
        this.content = bytes;
    }

    public ByteBuf wrap(Packet packet)
    {
        byte[] bytes = packet.serialize();
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byteBuf.writeInt(this.getMagicNumber());
        byteBuf.writeByte(this.getVersion());
        byteBuf.writeByte(packet.getAlgorithm().getType());
        byteBuf.writeByte(packet.getCommand().getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }

    public ETunnelProtocol wrapToProtocol(Packet packet)
    {
        byte[] bytes = packet.serialize();
        ETunnelProtocol protocol = new ETunnelProtocol();
        protocol.setMagicNumber(this.magicNumber);
        protocol.setVersion(this.getVersion());
        protocol.setAlgorithm(packet.getAlgorithm().getType());
        protocol.setCommand(packet.getCommand().getCommand());
        protocol.setLength(bytes.length);
        protocol.setContent(bytes);
        return protocol;
    }

    public int getMagicNumber()
    {
        return magicNumber;
    }

    public void setMagicNumber(int magicNumber)
    {
        this.magicNumber = magicNumber;
    }

    public byte getVersion()
    {
        return version;
    }

    public void setVersion(byte version)
    {
        this.version = version;
    }

    public byte getAlgorithm()
    {
        return algorithm;
    }

    public void setAlgorithm(byte algorithm)
    {
        this.algorithm = algorithm;
    }

    public byte getCommand()
    {
        return command;
    }

    public void setCommand(byte command)
    {
        this.command = command;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public byte[] getContent()
    {
        return content;
    }

    public void setContent(byte[] content)
    {
        this.content = content;
    }
}
