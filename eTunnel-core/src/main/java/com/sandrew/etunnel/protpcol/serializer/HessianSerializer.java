package com.sandrew.etunnel.protpcol.serializer;

import com.sandrew.etunnel.util.HessianUtil;

/**
 * @ClassName HessianSerializer
 * @Description
 * @Author summer
 * @Date 2024/3/20 14:11
 **/
public class HessianSerializer extends Serializer
{
    @Override
    public SerializerType getSerializerAlgorithm()
    {
        return SerializerType.HESSIAN;
    }

    @Override
    public byte[] serialize(Object obj)
    {
        if (null == obj)
        {
            return null;
        }
        return HessianUtil.javaObject2Byte(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clz)
    {
        if (null == bytes || bytes.length == 0)
        {
            return null;
        }
        return HessianUtil.byte2JavaObject(bytes, clz);
    }
}
