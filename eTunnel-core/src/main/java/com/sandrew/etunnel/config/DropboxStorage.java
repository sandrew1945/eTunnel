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
public class DropboxStorage implements UseProxy
{
    private String appkey;
    private String appsecret;
    @JsonProperty("refresh-token")
    private String refreshToken;
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

    public String getAppkey()
    {
        return appkey;
    }

    public void setAppkey(String appkey)
    {
        this.appkey = appkey;
    }

    public String getAppsecret()
    {
        return appsecret;
    }

    public void setAppsecret(String appsecret)
    {
        this.appsecret = appsecret;
    }

    public String getRefreshToken()
    {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
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

    public Proxy get_proxy()
    {
        return _proxy;
    }

    public void set_proxy(Proxy _proxy)
    {
        this._proxy = _proxy;
    }

    @Override
    public String toString()
    {
        return "DropboxStorage{" + "appkey='" + appkey + '\'' + ", appsecret='" + appsecret + '\'' + ", refreshToken='" + refreshToken + '\'' + ", useProxy=" + useProxy + ", root='" + root + '\'' + ", authority=" + authority + '}';
    }
}
