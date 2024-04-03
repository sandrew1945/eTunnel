package com.sandrew.etunnel.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.sandrew.etunnel.config.Configurations;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class TomlUtil
{
    private static Logger log = LoggerFactory.getLogger(TomlUtil.class);

    private static final TomlMapper mapper = new TomlMapper();

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
     * @Author summer
     * @Description Load the configuration file to POJO
     * @Date 14:10 2024/4/2
     * @Param [inputStream]
     * @return com.sandrew.etunnel.config.Configurations
     **/
    public static Configurations loadConfig(InputStream inputStream)
    {
        Configurations configurations = null;
        try
        {
            configurations = mapper.readValue(inputStream, Configurations.class);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return configurations;
    }

    /**
     * @Author summer
     * @Description Java object to toml string
     * @Date 10:03 2024/4/3
     * @Param [obj]
     * @return java.lang.String
     **/
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
}
