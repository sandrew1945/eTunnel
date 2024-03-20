module com.sandrew.etunnel.core {
    requires java.base;
    requires org.slf4j;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.joda;
    requires org.joda.time;
    requires hessian;

    exports com.sandrew.etunnel.protpcol to com.sandrew.etunnel.server, com.sandrew.etunnel.client, com.sandrew.etunnel.serialze;
    exports com.sandrew.etunnel.protpcol.serializer to com.sandrew.etunnel.server, com.sandrew.etunnel.client, com.sandrew.etunnel.serialze;


}