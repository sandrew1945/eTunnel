module com.sandrew.etunnel.test {
    requires com.sandrew.etunnel.core;
    requires org.junit.jupiter.api;
    requires org.slf4j;
    requires org.apache.commons.io;
    requires io.netty.buffer;

    exports com.sandrew.etunnel.test.serialize to org.junit.platform.commons, com.fasterxml.jackson.databind;
    exports com.sandrew.etunnel.test.util to org.junit.platform.commons;
    exports com.sandrew.etunnel.test.netty to org.junit.platform.commons;
}