module com.sandrew.etunnel.core {
    requires java.base;
    requires org.slf4j;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.joda;
    requires org.joda.time;
    requires hessian;
    requires org.apache.commons.codec;
    requires io.netty.buffer;
    requires io.netty.transport;
    requires io.netty.codec;
    requires java.sql;

    opens com.sandrew.etunnel.protpcol to hessian;

    exports com.sandrew.etunnel.protpcol to com.sandrew.etunnel.server, com.sandrew.etunnel.client, com.sandrew.etunnel.test;
    exports com.sandrew.etunnel.protpcol.serializer to com.sandrew.etunnel.server, com.sandrew.etunnel.client, com.sandrew.etunnel.test;
    exports com.sandrew.etunnel.util to com.sandrew.etunnel.test, com.sandrew.etunnel.client;
    exports com.sandrew.etunnel.handler;


}