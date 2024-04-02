package com.sandrew.etunnel.config;

/**
 * @ClassName ServerConfiguration
 * @Description
 * @Author summer
 * @Date 2024/4/2 13:48
 **/
public class ServerConfiguration
{
    private LocalConfiguration local;

    private RemoteConfiguration remote;

    public LocalConfiguration getLocal()
    {
        return local;
    }

    public void setLocal(LocalConfiguration local)
    {
        this.local = local;
    }

    public RemoteConfiguration getRemote()
    {
        return remote;
    }

    public void setRemote(RemoteConfiguration remote)
    {
        this.remote = remote;
    }

    @Override
    public String toString()
    {
        return "ServerConfiguration{" + "local=" + local + ", remote=" + remote + '}';
    }
}
