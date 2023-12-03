package com.bookapplication.exception;

import com.bookapplication.model.requestresponse.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.bookapplication.constants.AppConstants.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClientSideException.class)
    private ResponseEntity<ApiResponse<String>> badRequest(ClientSideException e) {
        return ResponseEntity.badRequest().body(ApiResponse.<String>builder()
                .message(FAILED)
                .status(FAILED_CLIENT_STATUS_CODE)
                .data(e.getMessage())
                .build()
        );
    }

    @ExceptionHandler(ServerSideException.class)
    private ResponseEntity<ApiResponse<String>> internalServerError(ServerSideException e) {
        return ResponseEntity.internalServerError().body(ApiResponse.<String>builder()
                .message(FAILED)
                .status(FAILED_SERVER_STATUS_CODE)
                .data(e.getMessage())
                .build());
    }

}
