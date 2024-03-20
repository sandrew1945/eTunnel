package com.sandrew.etunnel.util;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @ClassName HessianUtil
 * @Description
 * @Author summer
 * @Date 2024/3/19 16:20
 **/
public class HessianUtil
{

    private static Logger log = LoggerFactory.getLogger(HessianUtil.class);

    /**
     * @Author summer
     * @Description
     * @Date 11:12 2024/3/20
     * @Param [source, clz]
     * @return T
     **/
    public static <T> T byte2JavaObject(byte[] source, Class<T> clz)
    {
        if (null == source)
        {
            return null;
        }
        T result = null;
        Hessian2Input h2Input = null;
        try (ByteArrayInputStream bais = new ByteArrayInputStream(source))
        {
            h2Input = new Hessian2Input(bais);
            result = (T) h2Input.readObject();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        finally
        {
            if (null != h2Input)
            {
                try
                {
                    h2Input.close();
                }
                catch (IOException e)
                {
                    log.error(e.getMessage(), e);
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    public static byte[] javaObject2Byte(Object obj)
    {
        byte[] result = null;
        Hessian2Output h2Output = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream())
        {
            h2Output = new Hessian2Output(baos);
            h2Output.writeObject(obj);
            h2Output.flush();
            result = baos.toByteArray();
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        finally
        {
            if (null != h2Output)
            {
                try
                {
                    h2Output.close();
                }
                catch (IOException e)
                {
                    log.error(e.getMessage(), e);
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }
}
