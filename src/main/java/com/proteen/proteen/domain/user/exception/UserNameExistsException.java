package com.proteen.proteen.domain.user.exception;

import com.proteen.proteen.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserNameExistsException extends CustomException {
    public static final CustomException EXCEPTION = new UserNameExistsException();
    
    private UserNameExistsException() {
        super(HttpStatus.BAD_REQUEST, "해당 닉네임을 가진 유저가 이미 존재합니다.");
    }
}
