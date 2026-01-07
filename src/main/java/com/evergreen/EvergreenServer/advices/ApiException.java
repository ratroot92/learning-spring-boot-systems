package com.evergreen.EvergreenServer.advices;

import com.evergreen.EvergreenServer.utils.ApiError;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter

public class ApiException extends RuntimeException {
    private final ApiError apiError;

    public ApiException(ApiError apiError) {
        super(apiError.getMessage());
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }


    public static ApiException badRequest(String message) {
        ApiError apiError = new ApiError(message, HttpStatus.CONFLICT);
        return new ApiException(apiError);
    }

    public static ApiException unAuthenticated(String message) {
        message = Objects.equals(message, "") || message == null ? message : "UN_AUTHORIZED";
        ApiError apiError = new ApiError(message, HttpStatus.UNAUTHORIZED);
        return new ApiException(apiError);
    }
}
