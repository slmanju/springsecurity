//package com.manjula.securityjwt.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//import javax.validation.ConstraintViolationException;
//
//@RestControllerAdvice
//public class GlobalControllerExceptionHandler {
//
//    private static final Logger LOG = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
//
//    @ExceptionHandler(value = { ConstraintViolationException.class })
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiErrorResponse constraintViolationException(ConstraintViolationException ex) {
//        LOG.error(ex.getCause().toString());
//        return new ApiErrorResponse(400, "Bad Request");
//    }
//
//    @ExceptionHandler(value = { AccessDeniedException.class })
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    public ApiErrorResponse unauthorized(ConstraintViolationException ex) {
//        LOG.error(ex.getCause().toString());
//        return new ApiErrorResponse(401, "Please login");
//    }
//
//    @ExceptionHandler(value = { NoHandlerFoundException.class })
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ApiErrorResponse noHandlerFoundException(Exception ex) {
//        LOG.error(ex.getCause().toString());
//        return new ApiErrorResponse(404, "Resource Not Found");
//    }
//
//    @ExceptionHandler(value = { Exception.class })
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ApiErrorResponse unknownException(Exception ex) {
//        LOG.error(ex.getCause().toString());
//        return new ApiErrorResponse(500, "Internal Server Error");
//    }
//}
