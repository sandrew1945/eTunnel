package com.sandrew.etunnel.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

public class JsonUtil
{
    private static Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    static
    {
        mapper.registerModule(new JodaModule());
        // mapper.registerModule(BaseWriteOperationDeserializer.getModule());
        mapper.setTimeZone(DateTimeZone.getDefault().toTimeZone());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//         mapper.setDateFormat(new SimpleDateFormat("yyyyMMdd"));
    }

    public static void registerModule(SimpleModule module)
    {
        mapper.registerModule(module);
    }

    /**
     * java对象转json字符串
     *
     * @param obj
     * @return
     */
    public static String javaObject2String(Object obj)
    {
        String result = null;
        try
        {
            result = mapper.writeValueAsString(obj);
        }
        catch (JsonProcessingException e)
        {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * @return byte[]
     * @Author bin.wei
     * @Description java对象转字节数组
     * @Date 16:15 2021/9/2
     * @Param [obj]
     **/
    public static byte[] javaObject2Byte(Object obj)
    {
        byte[] result = null;
        try
        {
            result = mapper.writeValueAsBytes(obj);
        }
        catch (JsonProcessingException e)
        {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * @Author summer
     * @Description
     * @Date 14:38 2024/3/19
     * @Param [source, clz]
     * @return T
     **/
    public static <T> T byte2JavaObject(byte[] source, Class<T> clz)
    {
        T result = null;
        try
        {
            result = mapper.readValue(source, clz);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * json字符串转Java对象
     *
     * @param jsonStr
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T string2JavaObject(String jsonStr, Class<T> clz)
    {
        T result = null;
        try
        {
            result = mapper.readValue(jsonStr, clz);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    public static <T> T string2JavaObject(String jsonStr, Supplier<TypeReference> supplier)
    {
        T result = null;
        try
        {
            ObjectReader reader = mapper.readerFor(supplier.get());
            result = reader.readValue(jsonStr);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * json字符串转JSON对象
     *
     * @param jsonStr
     * @return
     */
    public static JsonNode string2JsonObject(String jsonStr)
    {
        JsonNode jsonNode = null;
        try
        {
            jsonNode = mapper.readTree(jsonStr);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return jsonNode;
    }

    /**
     * java对象转JSON对象
     *
     * @param javaObject
     * @return
     */
    public static JsonNode javaObject2JsonObject(Object javaObject)
    {
        JsonNode jsonNode = null;
        try
        {
            jsonNode = mapper.readTree(javaObject2String(javaObject));
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return jsonNode;
    }

    /**
     * @return T
     * @Author summer
     * @Description json对象转java对象
     * @Date 10:50 2021/10/13
     * @Param [jsonNode, clz]
     **/
    public static <T> T jsonObject2JavaObject(JsonNode jsonNode, Class<T> clz)
    {
        T t = null;
        try
        {
            t = mapper.treeToValue(jsonNode, clz);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return t;
    }

    /**
     * @return java.util.List<T>
     * @Author summer
     * @Description jsonArray对象转java集合
     * @Date 13:33 2021/10/13
     * @Param [jsonNode]
     **/
    public static <T> List<T> jsonArray2List(JsonNode jsonNode, Supplier<TypeReference> supplier)
    {
        List<T> list = null;
        try
        {
            ObjectReader reader = mapper.readerFor(supplier.get());
            list = reader.readValue(jsonNode);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    public static boolean validateJsonStr(String jsonFormStr)
    {
        boolean isJsonForm = false;
        try
        {
            mapper.readTree(jsonFormStr);
            isJsonForm = true;
        }
        catch (IOException e)
        {
            log.info(jsonFormStr + " doesn't a json form string.");
        }
        return isJsonForm;
    }
}
