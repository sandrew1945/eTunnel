package com.sandrew.etunnel.config;

import java.util.List;

/**
 * @ClassName DiskStorage
 * @Description
 * @Author summer
 * @Date 2024/4/2 13:51
 **/
public class DiskStorage
{
    private String id;

    private String path;

    private List<String> authority;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public List<String> getAuthority()
    {
        return authority;
    }

    public void setAuthority(List<String> authority)
    {
        this.authority = authority;
    }

    @Override
    public String toString()
    {
        return "DiskStorage{" + "id='" + id + '\'' + ", path='" + path + '\'' + ", authority=" + authority + '}';
    }
}
