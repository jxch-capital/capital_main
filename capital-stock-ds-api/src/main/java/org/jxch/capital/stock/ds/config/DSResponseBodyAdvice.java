package org.jxch.capital.stock.ds.config;

import org.jxch.capital.stock.ds.entity.vo.ResponseVO;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Calendar;

@RestControllerAdvice(basePackages = "org.jxch.capital.stock.ds.three.controller")
public class DSResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !returnType.getParameterType().equals(ResponseVO.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ResponseVO<?>)
            return body;

        return ResponseVO.builder()
                .data(body)
                .code(HttpStatus.OK.value())
                .msg(HttpStatus.OK.toString())
                .timestamp(Calendar.getInstance().getTimeInMillis())
                .build();
    }
}
