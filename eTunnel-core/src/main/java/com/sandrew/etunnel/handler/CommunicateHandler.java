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

    void sendData(Channel channel, S packet);

    R receiveData() throws InterruptedException;
}
