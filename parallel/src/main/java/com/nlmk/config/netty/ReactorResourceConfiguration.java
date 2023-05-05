package com.nlmk.config.netty;

import com.nlmk.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorResourceFactory;
import reactor.netty.resources.LoopResources;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app.netty", name = "enable")
public class ReactorResourceConfiguration {

    private final AppConfig appConfig;

    @Bean
    public ReactorResourceFactory resourceFactory() {
        final AppConfig.NettyConfig config = appConfig.getNetty();
        var reactorResourceFactory = new ReactorResourceFactory();
        reactorResourceFactory.setUseGlobalResources(false);
        reactorResourceFactory.setLoopResourcesSupplier(() -> LoopResources
                .create(config.getIoPoolPrefix(), config.getIoWorkerCount(), true));
        return reactorResourceFactory;
    }

}
