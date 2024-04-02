package com.sandrew.etunnel.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.toml.TomlMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.sandrew.etunnel.config.ServerConfiguration;
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
     * @return com.sandrew.etunnel.config.ServerConfiguration
     **/
    public static ServerConfiguration loadConfig(InputStream inputStream)
    {
        ServerConfiguration serverConfiguration = null;
        try
        {
            serverConfiguration = mapper.readValue(inputStream, ServerConfiguration.class);
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return serverConfiguration;
    }
}
