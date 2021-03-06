package com.example.g3.exception;

import com.example.g3.model.APIError;
import com.example.g3.model.ErrorResponse;
import com.example.g3.model.ErrorSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ProductNotFoundException.class})
    ResponseEntity<?> notFoundExceptionHandler(Exception e, ServletWebRequest request){
        APIError apiError = new APIError();
        apiError.setStatus(HttpStatus.NOT_FOUND);
        apiError.setCode("404");
        apiError.setTitle(e.getMessage());
        ErrorSource es = new ErrorSource();
        es.setParameter("id");
        es.setPointer("N/A");
        apiError.setSource(es);
        List<APIError> list = new ArrayList<>();
        list.add(apiError);
        return new ResponseEntity(new ErrorResponse(list), new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({DuplicateProductException.class})
    ResponseEntity<?> duplicateExceptionHandler(Exception e, ServletWebRequest request){
        APIError apiError = new APIError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setCode("400");
        apiError.setTitle(e.getMessage());
        ErrorSource es = new ErrorSource();
        es.setParameter("Name / Price");
        es.setPointer("N/A");
        apiError.setSource(es);
        List<APIError> list = new ArrayList<>();
        list.add(apiError);
        return new ResponseEntity(new ErrorResponse(list), new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        APIError apiError = new APIError();
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        ErrorSource es = new ErrorSource();
        es.setPointer(ex.getFieldError().getField());
        es.setParameter("N/A");
        apiError.setSource(es);
        apiError.setTitle(fieldError.getDefaultMessage());
        apiError.setCode("400");
        List<APIError> list = new ArrayList<>();
        list.add(apiError);
        return new ResponseEntity(new ErrorResponse(list), headers, apiError.getStatus());
    }
}

