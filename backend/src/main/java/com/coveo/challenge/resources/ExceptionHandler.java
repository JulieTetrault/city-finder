package com.coveo.challenge.resources;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.coveo.challenge.repositories.NotFoundException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherException(Exception e, WebRequest request) {
        if (isSpringWebException(e)) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        // We want to hide details of the exception to the clients, but still want to access those details in the logs
        log.info(e.getMessage(), e);
        String errorMessage = "Failed to call " + getRequestPath(request);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
    }

    private boolean isSpringWebException(Exception ex) {
        return ex.getClass().getPackage().getName().startsWith("org.springframework.web");
    }

    private String getRequestPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(ErrorResponse.builder().status(status.value()).error(status.getReasonPhrase()).message(message).build());
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorResponse {
        private Integer status;
        private String error;
        private String message;
    }
}
