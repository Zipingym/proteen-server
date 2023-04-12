package com.proteen.proteen.domain.user.presentation;

import com.proteen.proteen.domain.user.domain.User;
import com.proteen.proteen.domain.user.presentation.dto.request.CreateUserRequest;
import com.proteen.proteen.domain.user.presentation.dto.request.LoginRequest;
import com.proteen.proteen.domain.user.presentation.dto.response.UserLoginTokenResponse;
import com.proteen.proteen.domain.user.service.UserService;
import com.proteen.proteen.global.annotation.CheckToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateUserRequest request) {
        userService.register(request);
    }

    @PostMapping("/login")
    public UserLoginTokenResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @CheckToken
    @GetMapping("/myinfo")
    public User getUserInfo(@RequestAttribute User user) {
        return user;
    }
}
