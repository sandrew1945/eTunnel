package com.sandrew.etunnel.protpcol;

import com.sandrew.etunnel.protpcol.serializer.Serializer;

import java.io.Serializable;

/**
 * @ClassName Packet
 * @Description
 * @Author summer
 * @Date 2024/3/19 11:01
 **/
public abstract class Packet implements Serializable
{
    protected Serializer serializer;

    /**
     *  获取序列化算法
     * @return
     */
    public abstract byte getAlgorithm();

    /**
     *  获取指令
     * @return
     */
    public abstract byte getCommand();

    /**
     *  获取序列化后的字节数组
     * @return
     */
    public byte[] serialize()
    {
        return this.serializer.serialize(this);
    }

}
