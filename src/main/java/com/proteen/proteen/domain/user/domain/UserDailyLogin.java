package com.proteen.proteen.domain.user.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "UserDailyLogin")
@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDailyLogin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyId;

    private Long userId;

    private LocalDate date;
}
