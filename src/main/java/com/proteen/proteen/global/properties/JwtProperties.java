package com.proteen.proteen.global.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("jwt.secret")
public class JwtProperties {
    private String accessKey;
    private String refreshKey;
}
