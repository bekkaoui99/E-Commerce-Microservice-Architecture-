package com.hamzabekkaoui.customerservice.dto.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ExceptionResponse(

        String message,
        HttpStatus httpStatus,
        int code
) {
}
