package com.sandrew.etunnel.test.util;

import com.sandrew.etunnel.util.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @ClassName FileUtilTest
 * @Description
 * @Author summer
 * @Date 2024/3/21 14:13
 **/
public class FileUtilTest
{
    private static Logger log = LoggerFactory.getLogger(FileUtilTest.class);

    @Test
    @DisplayName("Get file MD5 test")
    public void getFileMD5Test()
    {
        System.out.println("Start getting file's md5 test");
        File file = new File("/foo/bar/file.txt"); // Replace with your file path
        String md5Hash = FileUtil.getFileMD5(file);
        System.out.println("MD5 Hash: " + md5Hash);
        Assertions.assertNotNull(md5Hash);
    }

    @Test
    @DisplayName("Get file MD5 by commons codec test")
    public void getFileMD5CodecTest()
    {
        System.out.println("Start getting file's md5 by apache commons codec test");
        File file = new File("/foo/bar/file.txt"); // Replace with your file path
        String md5Hash = FileUtil.getFileMD5ByCodec(file);
        System.out.println("MD5 Hash: " + md5Hash);
        Assertions.assertNotNull(md5Hash);
    }

    @Test
    @DisplayName("Get file extension test")
    public void getFileExtensionTest()
    {
        String fileExtension = FileUtil.getFileExtension(new File("/foo/bar/file.txt"));    // Replace with your file path
        log.info("fileExtension:" + fileExtension);
    }

    @Test
    @DisplayName("Path class test")
    public void pathClassTest()
    {
        try
        {
            Path abc = Path.of("/Users", "summer", "Desktop", "abc");
            log.info(abc.getFileName().toString());
            log.info(abc.getParent().toString());
            Assertions.assertEquals(abc.toString(), "/Users/summer/Desktop/abc");

            Path file = Path.of(URI.create("file:///Users/summer/Desktop/FeignExceptionDecoder.java"));

            log.info(file.toString());
            log.info("" + file.isAbsolute());

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Files class test")
    public void filesClassTest()
    {
        try
        {
            Path f = Files.createFile(Path.of("/Users", "summer", "Desktop", "abc"));
            log.info("isRegularFile:" + Files.isRegularFile(f));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


}
