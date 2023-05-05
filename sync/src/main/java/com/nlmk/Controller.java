package com.nlmk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
@RequestMapping
public class Controller {    @GetMapping("/synchronized/{millis}/{number}")
    String synchronizedTest(@PathVariable("millis") Long millis, @PathVariable("number") int number) {
        Object[] monitors = new Object[number];
        IntStream.range(0, number).forEach(i -> {
            monitors[i] = i;
            synchronized (monitors[i]) {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return number + " synchronized woke up after " + millis;
    }

}
