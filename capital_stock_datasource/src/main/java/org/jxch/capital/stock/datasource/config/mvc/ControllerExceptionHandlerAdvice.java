package org.jxch.capital.stock.datasource.config.mvc;

import lombok.extern.slf4j.Slf4j;
import org.jxch.capital.stock.datasource.entity.vo.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Calendar;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandlerAdvice {
    @ExceptionHandler({Exception.class})
    public ResponseVO<Object> exceptionHandler(Exception e) {
        log.error(e.getMessage());

        return ResponseVO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .msg(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                .timestamp(Calendar.getInstance().getTimeInMillis())
                .data(null)
                .build();
    }
}
