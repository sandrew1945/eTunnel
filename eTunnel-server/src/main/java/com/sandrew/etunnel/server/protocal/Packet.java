package com.sandrew.etunnel.server.protocal;


import com.sandrew.etunnel.server.protocal.serializer.Serializer;

import java.io.Serializable;

/**
 * @Author summer
 * @Description  Data packet
 * @Date 15:18 2024/3/18
 * @Param
 * @return
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
