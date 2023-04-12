package com.proteen.proteen.global.lib.jwt;

import com.proteen.proteen.domain.user.domain.User;
import com.proteen.proteen.domain.user.domain.repository.UserRepository;
import com.proteen.proteen.global.exception.global.InvalidTokenException;
import com.proteen.proteen.global.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final UserRepository userRepository;
    private final JwtProperties jwtProperties;
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String createToken(User user, TokenType tokenType) {

        Date nowDate = new Date();
        Calendar expiredDate = Calendar.getInstance();
        expiredDate.setTime(nowDate);

        String secretKey = "";

        if(tokenType.equals("ACCESS")){
            expiredDate.add(Calendar.DATE, 7);
            secretKey = jwtProperties.getAccessKey();
        }

        if(tokenType.equals("REFRESH")) {
            expiredDate.add(Calendar.DATE, 30);
            secretKey = jwtProperties.getRefreshKey();
        }

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", SIGNATURE_ALGORITHM);

        Map<String, Object> payloadMap = new HashMap<>();
        payloadMap.put("id", user.getUserId());

        JwtBuilder jwtBuilder = Jwts.builder()
                .setHeaderParams(headerMap)
                .setClaims(payloadMap)
                .setExpiration(expiredDate.getTime())
                .signWith(SIGNATURE_ALGORITHM, secretKey);

        return jwtBuilder.compact();
    }

    public User validateTokene(String token) {

        Claims claims = Jwts.parser().setSigningKey(jwtProperties.getAccessKey()).parseClaimsJws(token).getBody();

        return userRepository.findById(claims.get("userId", Long.class))
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);
    }
}
