package com.proteen.proteen.global.exception.global;

import com.proteen.proteen.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class TokenNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new TokenNotFoundException();

    private TokenNotFoundException() {
        super(HttpStatus.NOT_FOUND, "토큰을 찾지 못했습니다.");
    }
}
