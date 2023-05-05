package com.nlmk.controller;

import com.nlmk.service.LoadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class DataControllerImpl implements DataController {

    private final LoadService loadService;

    @Override
    public ResponseEntity<List<String>> testVirtual(Long millis, Long timeOut, List<Long> formIds) {
        return ResponseEntity.ok(loadService.testVirtual(formIds, millis, timeOut));
    }

    @Override
    public ResponseEntity<List<String>> testNotVirtual(Long millis, Long timeOut, List<Long> formIds) {
        return ResponseEntity.ok(loadService.testNotVirtual(formIds, millis, timeOut));
    }

    @Override
    public ResponseEntity<List<String>> testRecursive(Long millis, Long timeOut, List<Long> formIds) {
        return ResponseEntity.ok(loadService.testRecursive(formIds, millis, timeOut));
    }

    @Override
    public ResponseEntity<List<String>> testSync(Long millis, Long timeOut, List<Long> formIds) {
        return ResponseEntity.ok(loadService.testSync(formIds, millis, timeOut));
    }

}