package com.sandrew.etunnel.test.util;

import com.sandrew.etunnel.config.ConfigParser;
import com.sandrew.etunnel.config.DiskStorage;
import com.sandrew.etunnel.util.TomlUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * @ClassName FileUtilTest
 * @Description
 * @Author summer
 * @Date 2024/3/21 14:13
 **/
public class TomlUtilTest
{
    private static Logger log = LoggerFactory.getLogger(TomlUtilTest.class);

    @Test
    @DisplayName("Change POJO to string by toml")
    public void javaObject2StringTest()
    {
        DiskStorage diskStorage = new DiskStorage();
        diskStorage.setId("id");
        diskStorage.setPath("path");
        diskStorage.setRole(new ArrayList<String>());

        String tomlStr = TomlUtil.javaObject2String(diskStorage);
        log.info(tomlStr);
        Assertions.assertNotNull(tomlStr);
    }

    @Test
    @DisplayName("Config file parse test")
    public void ConfigFileParseTest()
    {
        InputStream is = ClassLoader.getSystemResourceAsStream("config.toml");
        ConfigParser parser = new ConfigParser();
        String configStr = TomlUtil.javaObject2String(parser.parse(is));
        log.info(configStr);
        Assertions.assertNotNull(configStr);
    }

}
