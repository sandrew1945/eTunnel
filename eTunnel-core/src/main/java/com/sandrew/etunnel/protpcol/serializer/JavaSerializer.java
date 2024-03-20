package com.sandrew.etunnel.protpcol.serializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @ClassName JavaSerializer
 * @Description
 * @Author summer
 * @Date 2024/3/19 10:50
 **/
public class JavaSerializer extends Serializer
{

    private static Logger log = LoggerFactory.getLogger(JavaSerializer.class);

    @Override
    public SerializerType getSerializerAlgorithm()
    {
        return SerializerType.JAVA;
    }

    @Override
    public byte[] serialize(Object obj)
    {
        byte[] bytes = null;
        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)
        )
        {
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();

        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return bytes;
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clz)
    {
        Object obj = null;
        try (
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream))
        {
            obj = objectInputStream.readObject();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        if (null != obj)
        {
            return clz.cast(obj);
        }
        else
        {
            return null;
        }
    }
}
