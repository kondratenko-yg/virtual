package com.nlmk;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class TomcatConnectorConfig implements TomcatConnectorCustomizer {
    private final Executor customExecutor;

    public TomcatConnectorConfig() {
        this.customExecutor = Executors.newVirtualThreadPerTaskExecutor();
    }

    @Override
    public void customize(Connector connector) {
        connector.getProtocolHandler().setExecutor(this.customExecutor);
    }
}
