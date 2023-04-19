package com.proteen.proteen.domain.user.service;

import com.proteen.proteen.domain.user.domain.User;
import com.proteen.proteen.domain.user.domain.repository.UserRepository;
import com.proteen.proteen.domain.user.exception.UserIdExistsException;
import com.proteen.proteen.domain.user.exception.UserNameExistsException;
import com.proteen.proteen.domain.user.exception.UserNotFoundException;
import com.proteen.proteen.domain.user.exception.UserPasswordMatchException;
import com.proteen.proteen.domain.user.presentation.dto.request.CreateUserRequest;
import com.proteen.proteen.domain.user.presentation.dto.request.LoginRequest;
import com.proteen.proteen.domain.user.presentation.dto.response.UserLoginTokenResponse;
import com.proteen.proteen.global.lib.jwt.JwtProvider;
import com.proteen.proteen.global.lib.jwt.TokenType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public void register(CreateUserRequest request) {

        if (userRepository.existsById(request.getId())) {
            throw UserIdExistsException.EXCEPTION;
        }

        if (userRepository.existsById(request.getName())) {
            throw UserNameExistsException.EXCEPTION;
        }

        User user = User.builder()
                .id(request.getId())
                .password(request.getPassword())
                .name(request.getName())
                .age(request.getAge())
                .gender(request.getGender())
                .build();

        userRepository.save(user);
    }

    @Transactional
    public UserLoginTokenResponse login(LoginRequest request) {

        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!request.getPassword().equals(user.getPassword())) {
            throw UserPasswordMatchException.EXCEPTION;
        }

        String accessToken = jwtProvider.createToken(user, TokenType.ACCESS);
        String refreshToken = jwtProvider.createToken(user, TokenType.REFRESH);

        return new UserLoginTokenResponse(accessToken, refreshToken);
    }

    public User getUserInfo(User user) {
        return user;
    }
}
