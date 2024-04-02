package com.sandrew.etunnel.config;

import java.util.Map;

/**
 * @ClassName RemoteConfiguration
 * @Description
 * @Author summer
 * @Date 2024/4/2 13:56
 **/
public class RemoteConfiguration
{
    private Map<String, DropboxStorage> dropbox;

    private Map<String, OneDriveStorage> onedrive;

    public Map<String, DropboxStorage> getDropbox()
    {
        return dropbox;
    }

    public void setDropbox(Map<String, DropboxStorage> dropbox)
    {
        this.dropbox = dropbox;
    }

    public Map<String, OneDriveStorage> getOnedrive()
    {
        return onedrive;
    }

    public void setOnedrive(Map<String, OneDriveStorage> onedrive)
    {
        this.onedrive = onedrive;
    }

    @Override
    public String toString()
    {
        return "RemoteConfiguration{" + "dropbox=" + dropbox + ", onedrive=" + onedrive + '}';
    }
}
