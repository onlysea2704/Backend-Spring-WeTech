package com.wetech.backend_spring_wetech.exception;

import com.wetech.backend_spring_wetech.dto.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        ApiResponse<Object> body = ApiResponse.of(400, "Validation failed", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ApiResponse<Object> body = ApiResponse.error("Malformed JSON request");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ApiResponse<Object> handleAccessDenied(AccessDeniedException ex) {
        return ApiResponse.of(403, "Access denied", null);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> handleEntityNotFound(EntityNotFoundException ex) {
        ApiResponse<Object> body = ApiResponse.of(404, ex.getMessage() == null ? "Resource not found" : ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiResponse<Object> body = ApiResponse.of(404, ex.getMessage() == null ? "Resource not found" : ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> handleRuntime(RuntimeException ex) {
        ApiResponse<Object> body = ApiResponse.of(500, ex.getMessage() == null ? "Internal server error" : ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<Object>> handleAll(Exception ex) {
        ApiResponse<Object> body = ApiResponse.of(500, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.of(401, "Sai tài khoản hoặc mật khẩu", null));
    }
}



