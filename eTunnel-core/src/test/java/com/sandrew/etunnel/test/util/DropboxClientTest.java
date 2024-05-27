package com.sandrew.etunnel.test.util;

import com.sandrew.etunnel.config.ConfigParser;
import com.sandrew.etunnel.config.Configurations;
import com.sandrew.etunnel.exception.DropboxException;
import com.sandrew.etunnel.util.DropboxUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static Logger log = LoggerFactory.getLogger(DropboxClientTest.class);
    private DropboxUtil dropboxUtil;

    @BeforeAll
    @DisplayName("Start to test dropbox client")
    public void begin()
    {
        InputStream is = ClassLoader.getSystemResourceAsStream("config.toml");
        ConfigParser parser = new ConfigParser();
        Configurations configurations = parser.parse(is);
        this.dropboxUtil = new DropboxUtil(configurations.getRemote().getDropbox().get("dp01"));

    }

    @Test
    @DisplayName("")
    public void test()
    {
        try
        {
            System.out.println(1);
            while (true)
            {
                log.info(this.dropboxUtil.shareFile("/foo/bar/456.jpg"));
                Thread.sleep(1000 * 60 * 10);
            }
        }
        catch (DropboxException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        catch (InterruptedException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }

    }
}
