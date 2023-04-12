package com.proteen.proteen.domain.user.exception;

import com.proteen.proteen.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않습니다.");
    }
}
