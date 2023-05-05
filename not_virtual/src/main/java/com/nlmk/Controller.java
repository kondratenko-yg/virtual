package com.nlmk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sleep")
public class Controller {
    @GetMapping("/{millis}/{number}")
    String getTest(@PathVariable("millis") Long millis, @PathVariable("number") Long number) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return number + " woke up after " + millis;
    }
}
