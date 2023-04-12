package com.proteen.proteen.domain.user.exception;

import com.proteen.proteen.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class UserIdExistsException extends CustomException {
    public static final CustomException EXCEPTION = new UserIdExistsException();

    private UserIdExistsException(){
        super(HttpStatus.BAD_REQUEST, "해당 아이디을 가진 유저가 이미 존재합니다.");
    }
}
