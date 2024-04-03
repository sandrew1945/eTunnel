package com.sandrew.etunnel.config;

/**
 * @ClassName ServerConfiguration
 * @Description
 * @Author summer
 * @Date 2024/4/3 10:12
 **/
public class ServerConfiguration
{
    private int port;

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    @Override
    public String toString()
    {
        return "ServerConfiguration{" + "port=" + port + '}';
    }
}
