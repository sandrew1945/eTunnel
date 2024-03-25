package com.sandrew.etunnel.protpcol.serializer;


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
     * @return byte
     * @Author summer
     * @Description Get serialize algorithm
     * @Date 15:23 2024/3/18
     * @Param []
     **/
    public abstract SerializerType getSerializerAlgorithm();

    /**
     * @return byte[]
     * @Author summer
     * @Description Do serialize
     * @Date 15:23 2024/3/18
     * @Param [obj]
     **/
    public abstract byte[] serialize(Object obj);

    /**
     * @return T
     * @Author summer
     * @Description Do deserialize
     * @Date 15:23 2024/3/18
     * @Param [bytes, clz]
     **/
    public abstract <T> T deserialize(byte[] bytes, Class<T> clz);

    /**
     * @return com.sandrew.etunnel.protpcol.serializer.Serializer
     * @Author summer
     * @Description Get serializer by type
     * @Date 15:23 2024/3/18
     * @Param [type]
     **/
    public static Serializer getSerializerByType(byte type)
    {
        for (SerializerType value : SerializerType.values())
        {
            if (value.getType() == type)
            {
                switch (value)
                {
                    case JAVA:
                    {
                        return new JavaSerializer();
                    }
                    case JSON:
                    {
                        return new JSONSerializer();
                    }
                    case HESSIAN:
                    {
                        return new HessianSerializer();
                    }
                }
            }
        }
        return null;
    }
}
