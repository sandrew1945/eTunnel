package com.sandrew.etunnel.server.protocal.serializer;


/**
 * @ClassName JSONSerializer
 * @Description
 * @Author summer
 * @Date 2024/3/18 16:16
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
        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clz)
    {
        return null;
    }
}
