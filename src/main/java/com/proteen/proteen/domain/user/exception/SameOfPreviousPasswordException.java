package com.proteen.proteen.domain.user.exception;

import com.proteen.proteen.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class SameOfPreviousPasswordException extends CustomException {
    public static final CustomException EXCEPTION = new SameOfPreviousPasswordException();

    private SameOfPreviousPasswordException() {
        super(HttpStatus.BAD_REQUEST, "이전 비밀번호와 동일합니다.");
    }
}
