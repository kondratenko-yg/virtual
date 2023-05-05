package com.nlmk.service;

import java.util.List;

public interface LoadService {

    List<String> testVirtual(List<Long> formIds, Long millis, Long timeOut);

    List<String> testNotVirtual(List<Long> formIds, Long millis, Long timeOut);

    List<String> testRecursive(List<Long> formIds, Long millis, Long timeOut);

    List<String> testSync(List<Long> formIds, Long millis, Long timeOut);

}
