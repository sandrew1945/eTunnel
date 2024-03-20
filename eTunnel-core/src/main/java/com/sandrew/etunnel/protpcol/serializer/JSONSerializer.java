package com.sandrew.etunnel.protpcol.serializer;

import com.sandrew.etunnel.util.JsonUtil;

/**
 * @ClassName JSONSerializer
 * @Description
 * @Author summer
 * @Date 2024/3/19 14:29
 **/
public class JSONSerializer extends Serializer
{
    @Override
    public SerializerType getSerializerAlgorithm()
    {
        return SerializerType.JSON;
    }

    @Override
    public byte[] serialize(Object obj)
    {
        if (null == obj)
        {
            return null;
        }
        return JsonUtil.javaObject2Byte(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clz)
    {
        if (null == bytes || bytes.length == 0)
        {
            return null;
        }
        return JsonUtil.byte2JavaObject(bytes, clz);
    }
}
