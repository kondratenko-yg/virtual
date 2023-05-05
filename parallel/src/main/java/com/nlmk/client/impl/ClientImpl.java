package com.nlmk.client.impl;

import com.nlmk.client.Client;
import com.nlmk.config.AppConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientImpl implements Client {

    private final AppConfig appConfig;

    private final WebClient client;

    @Override
    public CompletableFuture<String> testVirtual(Long millis, Long number, Long timeOut) {

        var config = appConfig.getWeb();
        var fullPath = config.getUrlSleep()+ MessageFormat.format(config.getPaths().getSleep(), millis, number);

        return getRequestTest(fullPath, String.class, timeOut).toFuture();
    }

    @Override
    public CompletableFuture<String> testNotVirtual(Long millis, Long number, Long timeOut) {

        var config = appConfig.getWeb();
        var fullPath = config.getUrlNotVirtual() + MessageFormat.format(config.getPaths().getSleep(), millis, number);

        return getRequestTest(fullPath, String.class, timeOut).toFuture();
    }
    @Override
    public CompletableFuture<String> testRecursive(Long millis, Long number, Long timeOut) {

        var config = appConfig.getWeb();
        var fullPath = config.getUrlFactorial() + MessageFormat.format(config.getPaths().getRecursive(), number);

        return getRequestTest(fullPath, String.class, timeOut).toFuture();
    }
    @Override
    public CompletableFuture<String> testSync(Long millis, Long number, Long timeOut) {

        var config = appConfig.getWeb();
        var fullPath = config.getUrlSync() + MessageFormat.format(config.getPaths().getSync(), millis, number);

        return getRequestTest(fullPath, String.class, timeOut).toFuture();
    }

    private <T> Mono<T> getRequestTest(String path, Class<T> elementTypeRef, Long timeout) {
        var config = appConfig.getWeb();
        return client.get()
                .uri(path)
                .retrieve()
                .bodyToMono(elementTypeRef)
                .timeout(Duration.ofSeconds(timeout));
    }

}
