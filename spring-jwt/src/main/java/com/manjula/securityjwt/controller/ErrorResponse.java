package com.manjula.securityjwt.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ErrorResponse {

    private int errorCode;
    private String errorMessage;
    private Map<String, Object> errors;

}
