//package com.manjula.securityjwt.controller;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorResponse> resourceNotFound(ResourceNotFoundException ex) {
//        System.out.println("--------------------");
//        ErrorResponse response = new ErrorResponse();
//        response.setErrorCode(404);
//        response.setErrorMessage(ex.getMessage());
//
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(value = { Exception.class })
//    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
//        System.out.println("*************************");
//        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//}
