package com.proteen.proteen.global.exception;

import com.proteen.proteen.global.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Response> customHandle(CustomException e){
        return new ResponseEntity<>(
                new Response(e.getStatus(), e.getMessage()),
                e.getStatus()
        );

    }
}
