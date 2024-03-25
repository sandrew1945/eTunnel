package com.sandrew.etunnel.protpcol.serializer;

/**
 * @ClassName SerializerType
 * @Description
 * @Author summer
 * @Date 2024/3/18 16:17
 **/
public enum SerializerType
{
    JAVA((byte) 0),
    JSON((byte) 1),
    HESSIAN((byte) 2);

    SerializerType(byte type)
    {
        this.type = type;
    }

    private byte type;

    public byte getType()
    {
        return type;
    }

}
