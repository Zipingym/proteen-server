package com.proteen.proteen.global.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class Response {
    private final int status;
    private final String message;

    public Response(HttpStatus httpStatus, String message) {
        this.status = httpStatus.value();
        this.message = message;
    }
}
