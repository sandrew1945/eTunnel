module com.sandrew.etunnel.core {
    requires java.base;
    requires org.slf4j;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.joda;
    requires com.fasterxml.jackson.dataformat.toml;
    requires org.joda.time;
    requires hessian;
    requires org.apache.commons.codec;
    requires io.netty.buffer;
    requires io.netty.transport;
    requires io.netty.codec;
    requires io.netty.common;
    requires io.netty.handler;
    requires java.sql;
    requires org.apache.commons.io;
    requires dropbox.core.sdk;
    requires org.apache.commons.lang3;


    opens com.sandrew.etunnel.protpcol to hessian;

    exports com.sandrew.etunnel.protpcol to com.sandrew.etunnel.server, com.sandrew.etunnel.client, com.fasterxml.jackson.databind, com.sandrew.etunnel.test;
    exports com.sandrew.etunnel.protpcol.serializer to com.sandrew.etunnel.server, com.sandrew.etunnel.client, hessian, com.sandrew.etunnel.test;
    exports com.sandrew.etunnel.util to com.sandrew.etunnel.test, com.sandrew.etunnel.client, com.sandrew.etunnel.server;
    exports com.sandrew.etunnel.handler;
    exports com.sandrew.etunnel.config;


}