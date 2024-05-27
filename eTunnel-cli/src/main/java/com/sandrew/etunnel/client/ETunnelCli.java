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

    public static String chunkedUploadFile(File largeFile)
    {
        ETunnelStreamClient client = new ETunnelStreamClient();
        client.connect("10.6.33.61", 7000);
        return client.chunkedFileUpload(largeFile);
    }


    public static void main(String[] args)
    {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            String fileId = uploadFile(new File("/Users/summer/Desktop/111.jpg"));
//            log.info("fileId =======> " + fileId);
            String s = chunkedUploadFile(new File("/Users/summer/Documents/Other/学习资料/Java/Hadoop/HADOOP权威指南 第3版  PDF电子书下载 带目录书签 完整版.pdf"));
            log.info("s =========> " + s);
        }));
    }
}
