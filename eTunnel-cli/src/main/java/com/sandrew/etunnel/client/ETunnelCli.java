package com.sandrew.etunnel.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @ClassName ETunnelCli
 * @Description
 * @Author summer
 * @Date 2024/3/25 15:50
 **/
public class ETunnelCli
{
    private static Logger log = LoggerFactory.getLogger(ETunnelCli.class);

    private  ETunnelClient client;

    public ETunnelClient getClient()
    {
        ETunnelClient client = new ETunnelClient();
        client.connect("127.0.0.1", 7000);
        return client;
    }

    public static String uploadFile(File file)
    {
        ETunnelClient client = new ETunnelClient();
        client.connect("127.0.0.1", 7000);
        return client.fileUpload(file);
//        client.run("127.0.0.1", 7000);
    }


    public static void main(String[] args)
    {
        String fileId = uploadFile(new File("/Users/summer/Desktop/新建文本文档(1).txt"));
        log.info("fileId =======> " + fileId);
    }
}
