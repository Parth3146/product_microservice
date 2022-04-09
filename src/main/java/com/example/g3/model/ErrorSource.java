package com.example.g3.model;


import lombok.Data;
import org.springframework.core.MethodParameter;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class ErrorSource {
    private String parameter;
    private String pointer;
}
