package com.sandrew.etunnel.util;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.http.HttpRequestor;
import com.dropbox.core.http.StandardHttpRequestor;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @ClassName DropboxUtil
 * @Description
 * @Author summer
 * @Date 2024/4/3 16:07
 **/
public class DropboxUtil
{
    public static void main(String[] args)
    {
        try
        {
//            DbxAppInfo dbxAppInfo = new DbxAppInfo("bqhgtdkykemr2q9", "3rycdef8r4b0a4d");
//            DbxRequestConfig dbxRequestConfig = new DbxRequestConfig("JavaDropboxTutorial/1.0", Locale.getDefault().toString());
//            DbxWebAuthNoRedirect dbxWebAuthNoRedirect = new DbxWebAuthNoRedirect(dbxRequestConfig, dbxAppInfo);
//            String authorizeUrl = dbxWebAuthNoRedirect.start();
//            System.out.println("1. Authorize: Go to URL and click Allow : " + authorizeUrl);
//            System.out.println("2. Auth Code: Copy authorization code and input here ");
//            String dropboxAuthCode = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
//            System.out.println("Input dropboxAuthCode : " + dropboxAuthCode);
//            DbxAuthFinish authFinish = dbxWebAuthNoRedirect.finish(dropboxAuthCode);
//            String authAccessToken = authFinish.getAccessToken();
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890));
            StandardHttpRequestor.Config.Builder builder = StandardHttpRequestor.Config.builder().withProxy(proxy);
            HttpRequestor req = new StandardHttpRequestor(builder.build());
            DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/etunnel").withHttpRequestor(req).build();
//            DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java").build();
            DbxClientV2 client = new DbxClientV2(config, "sl.ByzavYmtoSL8r6k348Hm-5_i8I1Mn2b5WJNgwp3nNwpv1POHuvN");
//            DbxClientV2 dbxClient = new DbxClientV2(config, authAccessToken);

            System.out.println("Dropbox Account Name: " + client.users().getCurrentAccount().getName());
            try (InputStream in = new FileInputStream("/foo/bar/file.txt")) {
                FileMetadata metadata = client.files().uploadBuilder("/foo/bar/file.txt")
                        .uploadAndFinish(in);
            }

        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
