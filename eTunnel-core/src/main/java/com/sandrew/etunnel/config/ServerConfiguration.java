package com.sandrew.etunnel.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @ClassName ServerConfiguration
 * @Description
 * @Author summer
 * @Date 2024/4/3 10:12
 **/
public class ServerConfiguration
{
    @JsonProperty("etunnel-port")
    private int etunnelPort;
    @JsonProperty("httpserver-port")
    private int httpserverPort;
    @JsonProperty("proxy-address")
    private String proxyAddress;
    @JsonProperty("proxy-port")
    private int proxyPort;

    public int getEtunnelPort()
    {
        return etunnelPort;
    }

    public void setEtunnelPort(int etunnelPort)
    {
        this.etunnelPort = etunnelPort;
    }

    public int getHttpserverPort()
    {
        return httpserverPort;
    }

    public void setHttpserverPort(int httpserverPort)
    {
        this.httpserverPort = httpserverPort;
    }

    public String getProxyAddress()
    {
        return proxyAddress;
    }

    public void setProxyAddress(String proxyAddress)
    {
        this.proxyAddress = proxyAddress;
    }

    public int getProxyPort()
    {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort)
    {
        this.proxyPort = proxyPort;
    }

    @Override
    public String toString()
    {
        return "ServerConfiguration{" + "etunnelPort=" + etunnelPort + ", httpserverPort=" + httpserverPort + ", proxyAddress='" + proxyAddress + '\'' + ", proxyPort=" + proxyPort + '}';
    }
}
