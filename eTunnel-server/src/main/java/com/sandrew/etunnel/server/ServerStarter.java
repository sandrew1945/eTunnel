package com.sandrew.etunnel.server;

import com.sandrew.etunnel.config.ConfigParser;
import com.sandrew.etunnel.config.Configurations;

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
            Configurations config = parser.parse(is);
            ETunnelServer server = new ETunnelServer(config);
            server.run();
            ETunnelHttpServer httpServer = new ETunnelHttpServer(config);
            httpServer.run();
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
