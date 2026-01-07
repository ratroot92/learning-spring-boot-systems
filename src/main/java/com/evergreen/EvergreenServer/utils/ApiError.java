package com.evergreen.EvergreenServer.utils;


import java.util.List;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private String message;
    private int httpCode;
    private HttpStatus httpStatus;
    private List<String> errors;

    public ApiError(String message) {
        this.message = message;
    }

    public ApiError(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.httpCode = httpStatus.value();

    }


    public ApiError(List<String> errorsList, HttpStatus httpStatus) {
        this.errors = errorsList;
        this.httpStatus = httpStatus;
        this.httpCode = httpStatus.value();

    }


}
