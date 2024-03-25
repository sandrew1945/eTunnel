module com.sandrew.etunnel.server {
    requires java.base;
    requires com.sandrew.etunnel.core;
    requires io.netty.all;
    requires io.netty.transport;
    requires io.netty.common;
    requires org.slf4j;

    exports com.sandrew.etunnel.server;
}