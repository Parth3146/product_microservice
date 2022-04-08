package com.example.g3.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class APIError {

    private HttpStatus status;
    private String code;
    private String title;
    private String source;
}
