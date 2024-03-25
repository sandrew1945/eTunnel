package com.sandrew.etunnel.server;

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
        ETunnelServer server = new ETunnelServer();
        server.run(7000);
    }
}
