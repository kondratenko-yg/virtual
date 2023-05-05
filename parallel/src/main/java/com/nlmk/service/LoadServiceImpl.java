package com.nlmk.service;

import com.nlmk.client.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoadServiceImpl implements LoadService {
    private final Client client;

    @Override
    public List<String> testVirtual(List<Long> numbers, Long millis, Long timeOut) {
        return test(numbers, number -> client.testVirtual(millis, number, timeOut));
    }

    @Override
    public List<String> testNotVirtual(List<Long> numbers, Long millis, Long timeOut) {
        return test(numbers, number -> client.testNotVirtual(millis, number, timeOut));
    }
    @Override
    public List<String> testRecursive(List<Long> numbers, Long millis, Long timeOut) {
        return test(numbers, number -> client.testRecursive(millis, number, timeOut));
    }

    @Override
    public List<String> testSync(List<Long> numbers, Long millis, Long timeOut) {
        return test(numbers, number -> client.testSync(millis, number, timeOut));
    }

    private List<String> test(List<Long> numbers, Function<Long, CompletableFuture<String>> action) {
        List<String> results = Collections.synchronizedList(new ArrayList<>(numbers.size()));
        CompletableFuture.allOf(
                numbers.stream()
                        .map(number -> action.apply(number)
                                .whenComplete((coreMboFormDto, throwable) -> {
                                    if (isNull(throwable)) {
                                        log.debug("OK", coreMboFormDto);
                                        results.add(coreMboFormDto);
                                    } else {
                                        log.error(throwable.getMessage());
                                    }
                                })
                        ).toList().toArray(new CompletableFuture[0])).join();
        return results;
    }
}