package com.proteen.proteen.domain.exercise.persentation.dto.request;

import com.proteen.proteen.domain.exercise.domain.type.ExerciseType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateRequest {

    private String title;

    private String body;

    private ExerciseType exerciseType;

    private double score;

    private int time;

    private int calorie;
}
