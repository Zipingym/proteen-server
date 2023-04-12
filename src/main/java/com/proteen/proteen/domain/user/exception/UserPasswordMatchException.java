package com.proteen.proteen.domain.user.exception;

import com.proteen.proteen.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserPasswordMatchException extends CustomException {
    public static final CustomException EXCEPTION = new UserPasswordMatchException();

    private UserPasswordMatchException() {
        super(HttpStatus.BAD_REQUEST, "비밀번호가 일치 하지 않습니다.");
    }
}
