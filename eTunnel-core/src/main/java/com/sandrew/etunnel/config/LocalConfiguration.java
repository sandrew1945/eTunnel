package com.sandrew.etunnel.config;

import java.util.Map;

/**
 * @ClassName LocalConfiguration
 * @Description
 * @Author summer
 * @Date 2024/4/2 13:49
 **/
public class LocalConfiguration
{
    private Map<String, DiskStorage> disk;

    public Map<String, DiskStorage> getDisk()
    {
        return disk;
    }

    public void setDisk(Map<String, DiskStorage> disk)
    {
        this.disk = disk;
    }

    @Override
    public String toString()
    {
        return "LocalConfiguration{" + "disk=" + disk + '}';
    }
}
