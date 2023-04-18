package com.proteen.proteen.domain.exercise.persentation.dto.request;

import com.proteen.proteen.domain.exercise.domain.type.ExerciseType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateRequest {

    private String title;

    private String body;

    private ExerciseType exerciseType;

    private double score;

    private Time time;

    private int calorie;
}
