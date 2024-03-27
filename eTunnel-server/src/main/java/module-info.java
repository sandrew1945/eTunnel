module com.sandrew.etunnel.server {
    requires java.base;
    requires com.sandrew.etunnel.core;
    requires io.netty.buffer;
    requires io.netty.transport;
    requires io.netty.codec;
    requires io.netty.common;
    requires io.netty.handler;
    requires org.slf4j;

    exports com.sandrew.etunnel.server;
}