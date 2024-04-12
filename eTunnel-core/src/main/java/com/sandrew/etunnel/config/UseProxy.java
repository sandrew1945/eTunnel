package com.sandrew.etunnel.config;

import java.net.Proxy;

/**
 * @ClassName UseProxy
 * @Description
 * @Author summer
 * @Date 2024/4/12 14:48
 **/
public interface UseProxy
{
    /**
     * @Author summer
     * @Description Add a proxy to storage node
     * @Date 14:49 2024/4/12
     * @Param [proxyAddress, Port]
     * @return void
     **/
    void useProxy(String proxyAddress, int proxyPort);

    /**
     * @Author summer
     * @Description Get this storage node's proxy
     * @Date 14:49 2024/4/12
     * @Param []
     * @return java.net.Proxy
     **/
    Proxy getProxy();
}
