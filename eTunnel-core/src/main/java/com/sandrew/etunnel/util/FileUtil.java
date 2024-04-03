package com.sandrew.etunnel.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName FileUtil
 * @Description
 * @Author summer
 * @Date 2024/3/21 13:50
 **/
public class FileUtil
{
    private static Logger log = LoggerFactory.getLogger(HessianUtil.class);

    /**
     * @return java.lang.String
     * @Author summer
     * @Description Get the specify file's md5
     * @Date 13:57 2024/3/21
     * @Param [file]
     **/
    public static String getFileMD5(File file)
    {
        if (null == file || !file.isFile())
        {
            return null;
        }
        try (FileInputStream fis = new FileInputStream(file))
        {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = fis.read(bytes, 0, 1024)) != -1)
            {
                digest.update(bytes, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, digest.digest());
            return bigInt.toString(16);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * @return java.lang.String
     * @Author summer
     * @Description Get the specify file's md5 use Apache Commons Codec
     * @Date 13:57 2024/3/21
     * @Param [file]
     **/
    public static String getFileMD5ByCodec(File file)
    {
        try
        {
            return DigestUtils.md5Hex(new FileInputStream(file));
        }
        catch (IOException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static boolean upload(String filePath, InputStream in) throws Exception
    {
        // 检测文件目录是否存在,如不存在,创建目录
        File dir = new File(getDirectory(filePath));
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        // 创建目标文件
        File file = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos))
        {
            // 缓冲读取要上传文件,并写到本地磁盘
            byte[] tmp = new byte[4 * 1024];
            int len = 0;
            while ((len = in.read(tmp)) >= 0)
            {
                bos.write(tmp, 0, len);
            }
            return true;
        }
        catch (Exception e)
        {
            throw new Exception("write file error", e);
        }
    }

    public static boolean upload(String filePath, byte[] fileBytes) throws Exception
    {
        // 检测文件目录是否存在,如不存在,创建目录
        File dir = new File(getDirectory(filePath));
        if (!dir.exists())
        {
            dir.mkdirs();
        }
        // 创建目标文件
        File file = new File(filePath);
        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos))
        {
            bos.write(fileBytes);
            return true;
        }
        catch (Exception e)
        {
            throw new Exception("write file error", e);
        }
    }


    public static boolean upload(String filePath, String fileName, InputStream in) throws Exception
    {
        return upload(filePath + File.separator + fileName, in);
    }

    public static boolean upload(String filePath, String fileName, byte[] fileBytes) throws Exception
    {
        return upload(filePath + File.separator + fileName, fileBytes);
    }


    public static byte[] download(String filePath) throws Exception
    {
        ByteArrayOutputStream bos = null;
        BufferedInputStream bis = null;
        try
        {
            // 获取要下载的文件
            File file = new File(filePath);
            bis = new BufferedInputStream(new FileInputStream(file));

            // 创建输出流
            bos = new ByteArrayOutputStream();
            byte[] tmp = new byte[4 * 1024];
            int len = 0;
            // 将文件写到输出流
            while ((len = bis.read(tmp)) >= 0)
            {
                bos.write(tmp, 0, len);
            }
            return bos.toByteArray();
        }
        catch (Exception e)
        {
            throw new Exception("get file error", e);
        }
        finally
        {
            if (null != bis)
            {
                bis.close();
            }
            if (null != bos)
            {
                try
                {
                    bos.close();
                }
                catch (IOException e)
                {
                    throw new Exception("close file error", e);
                }
            }
        }
    }

    public static byte[] download(String filePath, String fileName) throws Exception
    {
        return download(filePath + File.separator + fileName);
    }

    public static String createRandomFileName(String fileuploadFileName)
    {
        String nowTimeStr = ""; //保存当前时间
        String extName = ""; //扩展名
        String newFileName = ""; //保存的新文件名
        SimpleDateFormat sDateFormat;
        Random r = new Random();
        int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; //获取随机数
        sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); //时间格式化的格式
        nowTimeStr = sDateFormat.format(new Date()); //当前时间
        if (fileuploadFileName.lastIndexOf(".") >= 0)
        {
            extName = fileuploadFileName.substring(fileuploadFileName.lastIndexOf("."));
        }
        newFileName = nowTimeStr + rannum + extName;
        return newFileName;
    }

    /**
     * @return java.lang.String
     * @Author summer
     * @Description Get the file's extension
     * @Date 14:40 2024/3/27
     * @Param [file]
     **/
    public static String getFileExtension(File file)
    {
        String extension = FilenameUtils.getExtension(file.getName());
        return extension;
    }

    /**
     * @return byte[]
     * @Author summer
     * @Description File to byte[]
     * @Date 15:50 2024/4/1
     * @Param [file]
     **/
    public static byte[] file2Bytes(File file)
    {
        byte[] byteArray = new byte[(int) file.length()];
        try (FileInputStream inputStream = new FileInputStream(file))
        {
            inputStream.read(byteArray);
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return byteArray;
    }

    public void writeFile(String filePath, byte[] fileBytes)
    {
        String dirPath = getDirectory(filePath);
        File directory = new File(dirPath);
        if (!directory.exists())
        {
            directory.mkdirs();
        }
        try (FileOutputStream fos = new FileOutputStream(new File(filePath)))
        {

        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }


    /**
     * @return java.lang.String
     * @Author summer
     * @Description 根据文件全部路径获取所在文件夹
     * @Date 10:32 2024/3/22
     * @Param [realPath]
     **/
    private static String getDirectory(String realPath)
    {
        String directory = FilenameUtils.getPath(realPath);
        return directory;
    }

}
