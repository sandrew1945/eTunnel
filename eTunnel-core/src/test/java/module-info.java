module com.sandrew.etunnel.serialze {
    requires com.sandrew.etunnel.core;
    requires org.junit.jupiter.api;

    exports com.sandrew.etunnel.serialize to org.junit.platform.commons, com.fasterxml.jackson.databind;
}