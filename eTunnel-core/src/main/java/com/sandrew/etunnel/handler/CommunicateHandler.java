package com.sandrew.etunnel.handler;

import com.sandrew.etunnel.protpcol.Packet;
import io.netty.channel.Channel;

/**
 * @ClassName CommunicateHandler
 * @Description
 * @Author summer
 * @Date 2024/3/26 13:19
 **/
public interface CommunicateHandler<S, R extends Packet>
{

    /**
     * @Author summer
     * @Description send request
     * @Date 16:26 2024/3/26
     * @Param [channel, packet]
     * @return com.sandrew.etunnel.handler.CommunicateHandler
     **/
    CommunicateHandler sendData(Channel channel, S packet);

    /**
     * @Author summer
     * @Description receive response
     * @Date 16:26 2024/3/26
     * @Param []
     * @return R
     **/
    R receiveData() throws InterruptedException;

    /**
     * @Author summer
     * @Description sync send and receive data for short
     * @Date 16:25 2024/3/26
     * @Param [channel, packet]
     * @return R
     **/
    default R sendAndReceive(Channel channel, S packet) throws InterruptedException
    {
        return (R) sendData(channel, packet).receiveData();
    }
}
