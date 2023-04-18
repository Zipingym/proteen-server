package com.proteen.proteen.domain.exercise.exception;

import com.proteen.proteen.global.exception.CustomException;
import org.springframework.http.HttpStatus;

public class ExerciseNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new ExerciseNotFoundException();

    private ExerciseNotFoundException() {
        super(HttpStatus.NOT_FOUND, "운동기록이 존재하지 않습니다.");
    }
}
