package com.proteen.proteen.domain.user.presentation.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginTokenResponse {
    private String accessToken;
    private String refreshToken;
}
