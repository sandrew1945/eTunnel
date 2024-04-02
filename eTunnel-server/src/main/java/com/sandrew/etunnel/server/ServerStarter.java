package com.sandrew.etunnel.server;

import com.sandrew.etunnel.config.ConfigParser;

import java.io.InputStream;

/**
 * @ClassName ServerStarter
 * @Description
 * @Author summer
 * @Date 2024/3/25 14:46
 **/
public class ServerStarter
{
    public static void main(String[] args)
    {
        try
        {
            // parse the configuration
            InputStream is = ClassLoader.getSystemResourceAsStream("config.toml");
            ConfigParser parser = new ConfigParser();
            ETunnelServer server = new ETunnelServer(parser.parse(is));
            server.run(7000);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
