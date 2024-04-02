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

    List<String> role;

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

    public List<String> getRole()
    {
        return role;
    }

    public void setRole(List<String> role)
    {
        this.role = role;
    }

    @Override
    public String toString()
    {
        return "DiskStorage{" + "id='" + id + '\'' + ", path='" + path + '\'' + ", role=" + role + '}';
    }
}
