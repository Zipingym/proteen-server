package com.proteen.proteen.domain.user.domain;

import com.proteen.proteen.domain.user.domain.type.Gender;
import com.proteen.proteen.domain.user.exception.SameOfPreviousPasswordException;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String id;

    private String password;

    private String name;

    private int age;

    private Gender gender;

    public void updatePassword(String previousPassword, String newPassword) {
        if (previousPassword.equals(newPassword)){
            throw SameOfPreviousPasswordException.EXCEPTION;
        }
        this.password = newPassword;
    }

    @Builder
    public User(String id, String password, String name, int age, Gender gender) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}
