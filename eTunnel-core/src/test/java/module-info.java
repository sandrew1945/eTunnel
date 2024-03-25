module com.sandrew.etunnel.test {
    requires com.sandrew.etunnel.core;
    requires org.junit.jupiter.api;

    exports com.sandrew.etunnel.test.serialize to org.junit.platform.commons, com.fasterxml.jackson.databind;
    exports com.sandrew.etunnel.test.util to org.junit.platform.commons;
}