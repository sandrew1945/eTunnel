package com.sandrew.etunnel.server.protocal.serializer;

import java.io.Serializable;

/**
 * @Author summer
 * @Description
 * @Date 16:00 2024/3/18
 * @Param 
 * @return 
 **/
public abstract class Serializer implements Serializable
{
    /**
     * @Author summer
     * @Description Get serialize algorithm
     * @Date 15:23 2024/3/18
     * @Param []
     * @return byte
     **/
    public abstract SerializerType getSerializerAlgorithm();

    /**
     * @Author summer
     * @Description Do serialize
     * @Date 15:23 2024/3/18
     * @Param [obj]
     * @return byte[]
     **/
    public abstract byte[] serialize(Object obj);

    /**
     * @Author summer
     * @Description Do deserialize
     * @Date 15:23 2024/3/18
     * @Param [bytes, clz]
     * @return T
     **/
    public abstract <T> T deserialize(byte[] bytes, Class<T> clz);

    /**
     * @Author summer
     * @Description Get serializer by type
     * @Date 15:23 2024/3/18
     * @Param [type]
     * @return com.sandrew.etunnel.server.protocal.serializer.Serializer
     **/
    public static Serializer getSerializerByType(SerializerType type)
    {
        switch (type)
        {
            case JAVA:
            {
                return new JavaSerializer();
            }
            case JSON:
            {
                return new JSONSerializer();
            }
            case HESSION:
            {
                return null;
            }
            default:
            {
                return new JavaSerializer();
            }
        }

    }
}
