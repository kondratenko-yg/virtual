package com.nlmk.config.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Конфигурация http-client.
 */
@Configuration
public class WebClientConfiguration {

    @Value("${spring.codec.max-in-memory-size}")
    private int maxCodecSize;

    @Bean
    public WebClient webClient() {
        return WebClient.builder().exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(maxCodecSize)).build()).build();
    }

}
