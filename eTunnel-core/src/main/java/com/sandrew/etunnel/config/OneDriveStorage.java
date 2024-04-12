package com.sandrew.etunnel.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

/**
 * @ClassName DropboxStorage
 * @Description
 * @Author summer
 * @Date 2024/4/2 13:56
 **/
public class OneDriveStorage implements UseProxy
{
    private String account;
    private String token;
    @JsonProperty("use-proxy")
    private boolean useProxy;
    private String root;
    private List<String> authority;

    @JsonIgnore
    private Proxy _proxy = Proxy.NO_PROXY;

    @Override
    public void useProxy(String proxyAddress, int proxyPort)
    {
        this._proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort));
    }

    @Override
    public Proxy getProxy()
    {
        return this._proxy;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }


    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public boolean isUseProxy()
    {
        return useProxy;
    }

    public void setUseProxy(boolean useProxy)
    {
        this.useProxy = useProxy;
    }

    public String getRoot()
    {
        return root;
    }

    public void setRoot(String root)
    {
        this.root = root;
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
        return "OneDriveStorage{" + "account='" + account + '\'' + ", token='" + token + '\'' + ", useProxy=" + useProxy + ", root='" + root + '\'' + ", authority=" + authority + '}';
    }
}
