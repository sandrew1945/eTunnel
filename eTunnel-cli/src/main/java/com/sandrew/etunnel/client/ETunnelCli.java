package com.sandrew.etunnel.client;

import java.io.File;

/**
 * @ClassName ETunnelCli
 * @Description
 * @Author summer
 * @Date 2024/3/25 15:50
 **/
public class ETunnelCli
{
    public static void uploadFile(File file)
    {
        ETunnelClient client = new ETunnelClient();
        client.run("127.0.0.1", 7000);
    }


    public static void main(String[] args)
    {
        uploadFile(null);
    }
}
