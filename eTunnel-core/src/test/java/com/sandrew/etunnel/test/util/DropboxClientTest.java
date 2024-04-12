package com.sandrew.etunnel.test.util;

import com.sandrew.etunnel.config.ConfigParser;
import com.sandrew.etunnel.config.Configurations;
import com.sandrew.etunnel.util.DropboxUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.InputStream;

/**
 * @ClassName DropboxClientTest
 * @Description
 * @Author summer
 * @Date 2024/4/3 16:02
 **/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DropboxClientTest
{

    @BeforeAll
    @DisplayName("Start to test dropbox client")
    public void begin()
    {
        InputStream is = ClassLoader.getSystemResourceAsStream("config.toml");
        ConfigParser parser = new ConfigParser();
        Configurations configurations = parser.parse(is);
        DropboxUtil dropboxUtil = new DropboxUtil(configurations.getRemote().getDropbox().get("dp01"));

    }

    @Test
    @DisplayName("")
    public void test()
    {
        System.out.println(1);

    }
}
