package com.sandrew.etunnel.util;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.http.HttpRequestor;
import com.dropbox.core.http.StandardHttpRequestor;
import com.dropbox.core.v2.DbxClientV2;

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
            Proxy proxy = new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", 7891));
            StandardHttpRequestor.Config.Builder builder = StandardHttpRequestor.Config.builder().withProxy(proxy);
            HttpRequestor req = new StandardHttpRequestor(builder.build());
            DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").withHttpRequestor(req).build();
            DbxClientV2 client = new DbxClientV2(config, "sl.ByrCWZFOYoz9lKFGJ0ada3Z4ttMoKm5krIMKOt0bW5AJj9ybbRhVM28UtwCBXV4Rd9CBEF");
//            DbxClientV2 dbxClient = new DbxClientV2(config, authAccessToken);

            System.out.println("Dropbox Account Name: " + client.users().getCurrentAccount().getName());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }
}
