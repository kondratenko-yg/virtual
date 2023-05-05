package com.nlmk.client;

import java.util.concurrent.CompletableFuture;

public interface Client {

    CompletableFuture<String> testVirtual(Long millis, Long number, Long timeOut);

    CompletableFuture<String> testNotVirtual(Long millis, Long number, Long timeOut);

    CompletableFuture<String> testRecursive(Long millis, Long number, Long timeOut);

    CompletableFuture<String> testSync(Long millis, Long number, Long timeOut);

}
