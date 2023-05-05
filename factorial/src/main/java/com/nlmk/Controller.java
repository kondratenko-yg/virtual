package com.nlmk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping
public class Controller {
    @GetMapping("/recursive/{number}")
    String recursive(@PathVariable("number") Long number) {
        return number + "! = " + calculateFactorial(number);
    }

    private static BigDecimal calculateFactorial (Long number) {
        BigDecimal factorial = BigDecimal.ONE;
        for (int i = 1; i <= number; i++) {
            factorial = factorial.multiply(new BigDecimal(i));
        }
        return factorial;
    }
}
