package com.sandrew.etunnel.client;

import com.sandrew.etunnel.protpcol.serializer.JavaSerializer;
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
        client.connect("10.6.33.61", 7000);
        return client.fileUpload(file, new JavaSerializer());
//        client.run("127.0.0.1", 7000);
    }


    public static void main(String[] args)
    {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            String fileId = uploadFile(new File("C:\\Users\\Weib\\Desktop\\3.中证易签流程框架梳理.pdf"));
            log.info("fileId =======> " + fileId);
        }));
    }
}
