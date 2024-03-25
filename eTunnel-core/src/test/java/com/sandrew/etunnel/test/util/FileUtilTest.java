package com.sandrew.etunnel.test.util;

import com.sandrew.etunnel.util.FileUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @ClassName FileUtilTest
 * @Description
 * @Author summer
 * @Date 2024/3/21 14:13
 **/
public class FileUtilTest
{
    @Test
    @DisplayName("JSON serializer test")
    public void getFileMD5Test()
    {
        System.out.println("Start getting file's md5 test");
        File file = new File("/foo/bar/file.txt"); // Replace with your file path
        String md5Hash = FileUtil.getFileMD5(file);
        System.out.println("MD5 Hash: " + md5Hash);
        Assertions.assertNotNull(md5Hash);
    }

    @Test
    @DisplayName("JSON serializer test")
    public void getFileMD5CodecTest()
    {
        System.out.println("Start getting file's md5 by apache commons codec test");
        File file = new File("/foo/bar/file.txt"); // Replace with your file path
        String md5Hash = FileUtil.getFileMD5ByCodec(file);
        System.out.println("MD5 Hash: " + md5Hash);
        Assertions.assertNotNull(md5Hash);
    }
}
