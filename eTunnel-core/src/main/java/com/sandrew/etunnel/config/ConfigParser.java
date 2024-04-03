package com.sandrew.etunnel.config;

import com.sandrew.etunnel.util.TomlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * @ClassName ConfigParser
 * @Description
 * @Author summer
 * @Date 2024/4/2 14:08
 **/
public class ConfigParser
{
    private static Logger log = LoggerFactory.getLogger(ConfigParser.class);

    public Configurations parse(InputStream is)
    {
        log.info("Parse the configurations...");
        try
        {
            Configurations configurations = TomlUtil.loadConfig(is);
            log.debug("Configurations :"  + configurations);
            return configurations;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
