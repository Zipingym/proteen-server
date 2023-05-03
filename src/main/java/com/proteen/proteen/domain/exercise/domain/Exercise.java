package com.proteen.proteen.domain.exercise.domain;

import com.proteen.proteen.domain.exercise.domain.type.ExerciseType;
import com.proteen.proteen.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exercise {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exerciseId;

    private String title;

    private String body;

    private ExerciseType exerciseType;

    private String videoUrl;

    private double score;

    private String time;

    private int calorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private User user;

    public void injectUser(User user) {
        this.user = user;
    }

    public void injectFile(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Builder
    public Exercise(String title, String body, ExerciseType exerciseType, double score, String  time, int calorie, User user) {
        this.title = title;
        this.body = body;
        this.exerciseType = exerciseType;
        this.score = score;
        this.time = time;
        this.calorie = calorie;
        this.user = user;
    }
}
