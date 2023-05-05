package com.nlmk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {

    private NettyConfig netty;

    private Web web;

    @Data
    public static class Web {

        private String urlNotVirtual;

        private String urlSleep;

        private String urlFactorial;

        private String urlSync;

        private Path paths;

        private long maxAttempts;

        private long minBackoffMs;

        private long commonTimeoutSec;
    }


    @Data
    public static class Path {

        private String sleep;

        private String recursive;

        private String sync;

    }

    @Data
    public static class NettyConfig {

        private boolean enable;

        private int ioWorkerCount;

        private String ioPoolPrefix;

    }


}
