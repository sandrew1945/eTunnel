package com.sandrew.etunnel.util;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.http.HttpRequestor;
import com.dropbox.core.http.StandardHttpRequestor;
import com.dropbox.core.oauth.DbxCredential;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.dropbox.core.v2.files.CreateFolderResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.sharing.CreateSharedLinkWithSettingsError;
import com.dropbox.core.v2.sharing.CreateSharedLinkWithSettingsErrorException;
import com.sandrew.etunnel.config.DropboxStorage;
import com.sandrew.etunnel.exception.DropboxException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName DropboxUtil
 * @Description
 * @Author summer
 * @Date 2024/4/3 16:07
 **/
public class DropboxUtil
{
    private static Logger log = LoggerFactory.getLogger(DropboxUtil.class);
    private static final String ACCESS_TOKEN = "";

    private DropboxStorage dropboxStorage;
    private DbxClientV2 dbxClient;

    public DropboxUtil(DropboxStorage aDropboxStorage)
    {
        try
        {
            this.dropboxStorage = aDropboxStorage;
            // authenticate the user.
            DbxRequestConfig config = getRequestConfig(dropboxStorage.isUseProxy());
            DbxCredential credential = new DbxCredential(ACCESS_TOKEN, 14400l, this.dropboxStorage.getRefreshToken(), this.dropboxStorage.getAppkey(), this.dropboxStorage.getAppsecret());
            dbxClient = new DbxClientV2(config, credential);
            refreshAccessToken();
            log.debug("Dropbox Account Name: " + dbxClient.users().getCurrentAccount().getName());
        }
        catch (DbxException e)
        {
            throw new RuntimeException(e);
        }
    }

    private DbxAppInfo getAppInfo()
    {
        return new DbxAppInfo(this.dropboxStorage.getAppkey(), this.dropboxStorage.getAppsecret());
    }

    private DbxRequestConfig getRequestConfig(boolean useProxy)
    {
        DbxRequestConfig config = null;
        StandardHttpRequestor.Config.Builder builder = StandardHttpRequestor.Config.builder().withProxy(dropboxStorage.getProxy());
        HttpRequestor req = new StandardHttpRequestor(builder.build());
        DbxRequestConfig.Builder requestConfigBuilder = DbxRequestConfig.newBuilder("dropbox/etunnel");
        if (useProxy)
        {
            requestConfigBuilder.withHttpRequestor(req);
        }
        config = requestConfigBuilder.build();
        return config;
    }

    /**
     * @Author summer
     * @Description Refresh access token
     * @Date 16:01 2024/4/12
     * @Param []
     * @return void
     **/
    public void refreshAccessToken()
    {
        try
        {
            dbxClient.refreshAccessToken();
        }
        catch (DbxException e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /* returns Dropbox size in GB */
    //    public long getDropboxSize() throws DbxException
    //    {
    //        long dropboxSize = 0;
    //        DbxAccountInfo dbxAccountInfo = dbxClient.getAccountInfo();
    //        // in GB :)
    //        dropboxSize = dbxAccountInfo.quota.total / 1024 / 1024 / 1024;
    //        return dropboxSize;
    //    }

    public void uploadToDropbox(File sourceFile, String destPath, String destFileName) throws DbxException, IOException
    {
        try (FileInputStream fis = new FileInputStream(sourceFile))
        {
            destPath = StringUtils.isEmpty(destPath) ? "/" : destPath;
            destPath = StringUtils.endsWith(destPath, "/") ? StringUtils.removeEnd(destPath, "/") : destPath;
            FileMetadata metadata = dbxClient.files().uploadBuilder(StringUtils.isEmpty(destPath) ? "/" : destPath + "/" + destFileName).uploadAndFinish(fis);
            //            String sharedUrl = dbxClient.sharing().createSharedLinkWithSettings(StringUtils.isEmpty(destPath)? "/" : destPath + "/" + destFileName).getUrl();
            //            String sharedUrl = dbxClient.createShareableUrl("/" + fileName);
            //            System.out.println("Uploaded: " + metadata.getPathLower() + metadata.getName() + " URL: " + sharedUrl);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
    }

    public String createFolder(String folderName) throws DropboxException
    {
        String path = null;
        try
        {
            CreateFolderResult result = dbxClient.files().createFolderV2("/" + folderName);
            path = result.getMetadata().getPathDisplay();
        }
        catch (CreateFolderErrorException e)
        {
            switch (e.errorValue.getPathValue().tag())
            {
                case CONFLICT -> throw new DropboxException("Directory is exist", e);
                case DISALLOWED_NAME -> throw new DropboxException("Directory name is not allowed", e);
                case INSUFFICIENT_SPACE -> throw new DropboxException("Not enough space", e);
                case MALFORMED_PATH -> throw new DropboxException("Directory name is malformed", e);
                case NO_WRITE_PERMISSION -> throw new DropboxException("No permission", e);
                case OTHER -> throw new DropboxException("Unknown error", e);
            }
        }
        catch (DbxException e)
        {
            throw new RuntimeException(e);
        }
        return path;
    }

    //    public void listDropboxFolders(String folderPath) throws DbxException
    //    {
    //        dbxClient.files().listRevisions()
    //        DbxEntry.WithChildren listing = dbxClient.getMetadataWithChildren(folderPath);
    //        System.out.println("Files List:");
    //        for (DbxEntry child : listing.children)
    //        {
    //            System.out.println("	" + child.name + ": " + child.toString());
    //        }
    //    }

    public void downloadFromDropbox(String fileName) throws DbxException, IOException
    {
        FileOutputStream outputStream = new FileOutputStream("/Users/summer/Desktop/222.jpg");
        try
        {
            FileMetadata fileMetadata = dbxClient.files().download("/" + fileName).download(outputStream);
            //            DbxEntry.File downloadedFile = dbxClient.getFile("/" + fileName, null, outputStream);
            System.out.println("Metadata: " + fileMetadata.getName());
        }
        finally
        {
            outputStream.close();
        }
    }

    /**
     * @return java.lang.String
     * @Author summer
     * @Description Share a file's url from dropbox
     * @Date 11:03 2024/4/9
     * @Param [absoluteFilePath]
     **/
    public String shareFile(String absoluteFilePath) throws DropboxException
    {

        try
        {
            String sharedUrl = dbxClient.sharing().createSharedLinkWithSettings(absoluteFilePath).getUrl();
            return sharedUrl;
        }
        catch (DbxException e)
        {
            if (e instanceof CreateSharedLinkWithSettingsErrorException)
            {
                CreateSharedLinkWithSettingsErrorException ce = (CreateSharedLinkWithSettingsErrorException) e;
                CreateSharedLinkWithSettingsError errorValue = ce.errorValue;
                log.debug("Error tag : " + errorValue.tag().toString());
                if ("SHARED_LINK_ALREADY_EXISTS".equals(errorValue.tag().toString()))
                {
                    // get exist file's share link
                    return errorValue.getSharedLinkAlreadyExistsValue().getMetadataValue().getUrl();
                }
            }
            log.error(e.getMessage(), e);
            throw new DropboxException("Share file failure.", e);
        }
    }

    public String getShareLink(String absoluteFilePath) throws DropboxException
    {
        try
        {
            //            return dbxClient.sharing().get;
            return "";
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new DropboxException("Get file of " + absoluteFilePath + " share link failure.", e);
        }
    }
}
