package com.manjula.securityjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class SimpleErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    public ResponseEntity<?> error(HttpServletRequest request) {
        Map<String, Object> errors = getErrorAttributes(request);
        HttpStatus statusCode = getStatus(request);

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .errorCode(statusCode.value())
                .errorMessage("Hello error")
                .errors(errors)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.OK);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    /**
     * this.status = status;
     this.error = (String) errorAttributes.get("error");
     this.message = (String) errorAttributes.get("message");
     this.timeStamp = errorAttributes.get("timestamp").toString();
     this.trace = (String) errorAttributes.get("trace");
     * @param request
     * @return
     */
    private Map<String, Object> getErrorAttributes(HttpServletRequest request) {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        return errorAttributes.getErrorAttributes(servletWebRequest, true);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception var4) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }

}
