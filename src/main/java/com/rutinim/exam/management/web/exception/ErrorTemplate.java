package com.rutinim.exam.management.web.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorTemplate {
    @JsonProperty("status")
    private HttpStatus httpStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamps = LocalDateTime.now();

    @JsonProperty("message")
    private String errorMessage;

    public ErrorTemplate(String errorMessage, HttpStatus httpStatus){
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public ErrorTemplate(Throwable e, HttpStatus httpStatus){
        this.httpStatus = httpStatus;
        this.errorMessage = e.getMessage();
    }
}
