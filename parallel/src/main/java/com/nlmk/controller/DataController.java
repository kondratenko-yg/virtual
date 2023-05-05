package com.nlmk.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("/api/v1")
public interface DataController {

    @PostMapping("/virtual/{millis}/{to}")
    ResponseEntity<List<String>> testVirtual(@PathVariable("millis") Long millis,
                                             @PathVariable("to") Long timeOut,
                                             @RequestBody List<Long> formIds);

    @PostMapping("/not/virtual/{millis}/{to}")
    ResponseEntity<List<String>> testNotVirtual(@PathVariable("millis") Long millis,
                                                @PathVariable("to") Long timeOut,
                                                @RequestBody List<Long> formIds);

    @PostMapping("/recursive/{millis}/{to}")
    ResponseEntity<List<String>> testRecursive(@PathVariable("millis") Long millis,
                                               @PathVariable("to") Long timeOut,
                                               @RequestBody List<Long> formIds);

    @PostMapping("/synchronized/{millis}/{to}")
    ResponseEntity<List<String>> testSync(@PathVariable("millis") Long millis,
                                          @PathVariable("to") Long timeOut,
                                          @RequestBody List<Long> formIds);

}

