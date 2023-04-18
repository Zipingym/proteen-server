package com.proteen.proteen.domain.exercise.service;

import com.proteen.proteen.domain.exercise.domain.Exercise;
import com.proteen.proteen.domain.exercise.domain.repository.ExerciseRepository;
import com.proteen.proteen.domain.exercise.persentation.dto.request.CreateRequest;
import com.proteen.proteen.domain.user.domain.User;
import com.proteen.proteen.global.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final S3Uploader s3Uploader;
    private final ExerciseRepository exerciseRepository;

    public void register(CreateRequest request, MultipartFile file, User user) {
        Exercise exercise = Exercise.builder()
                .title(request.getTitle())
                .body(request.getBody())
                .exerciseType(request.getExerciseType())
                .videoUrl(s3Uploader.uploadImage(file))
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