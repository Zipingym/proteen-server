package com.proteen.proteen.global.interceptor;

import com.proteen.proteen.domain.user.domain.User;
import com.proteen.proteen.global.annotation.CheckToken;
import com.proteen.proteen.global.exception.global.TokenNotFoundException;
import com.proteen.proteen.global.lib.jwt.JwtProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtProvider jwt;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (!handlerMethod.getMethod().isAnnotationPresent(CheckToken.class)) {
            return true;
        }

        String token = jwt.extract(request, "Bearer");
        if (token.equals("")) {
            throw TokenNotFoundException.EXCEPTION;
        }

        User user = jwt.validateToken(token);
        request.setAttribute("user", user.getUserId());

        return true;
    }

}
