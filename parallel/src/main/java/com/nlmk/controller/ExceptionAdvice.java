package com.nlmk.controller;

import com.nlmk.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleException(RuntimeException e) {
        var errorDto = new ErrorResponseDto();
        errorDto.setDescription(e.getMessage());
        log.error("ERROR", e);
        return ResponseEntity.internalServerError().body(errorDto);
    }

}
