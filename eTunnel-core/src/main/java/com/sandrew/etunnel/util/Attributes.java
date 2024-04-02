package com.sandrew.etunnel.util;

import com.sandrew.etunnel.config.ServerConfiguration;
import io.netty.util.AttributeKey;

/**
 * Created by summer on 2019/9/4.
 */
public interface Attributes
{
    static final AttributeKey<ServerConfiguration> SERVER_CONFIG = AttributeKey.newInstance("server.config");



}
