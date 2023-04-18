package com.proteen.proteen.domain.exercise.service;

import com.proteen.proteen.domain.exercise.domain.Exercise;
import com.proteen.proteen.domain.exercise.domain.repository.ExerciseRepository;
import com.proteen.proteen.domain.exercise.persentation.dto.request.CreateRequest;
import com.proteen.proteen.domain.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public void register(CreateRequest request, User user) {
        Exercise exercise = Exercise.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .exerciseType(request.getExerciseType())
                .score(request.getScore())
                .time(request.getTime())
                .calorie(request.getCalorie())
                .build();

        exercise.injectUser(user);

        exerciseRepository.save(exercise);
    }

    public Exercise getExerciseById(Long exerciseId, User user) {
        return exerciseRepository.findByExerciseIdAndUser(exerciseId, user)
                .orElseThrow(RuntimeException::new);
    }

    public List<Exercise> getExcrciseList(User user) {
        return exerciseRepository.findAllByUser(user)
                .orElseThrow(RuntimeException::new);
    }

}







