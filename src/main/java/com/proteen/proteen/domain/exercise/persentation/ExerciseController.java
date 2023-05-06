package com.proteen.proteen.domain.exercise.persentation;

import com.proteen.proteen.domain.exercise.domain.Exercise;
import com.proteen.proteen.domain.exercise.domain.type.ExerciseType;
import com.proteen.proteen.domain.exercise.persentation.dto.request.CreateRequest;
import com.proteen.proteen.domain.exercise.persentation.dto.response.ExerciseRankingInterface;
import com.proteen.proteen.domain.exercise.service.ExerciseService;
import com.proteen.proteen.domain.user.domain.User;
import com.proteen.proteen.global.annotation.CheckToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @CheckToken
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody CreateRequest request, @RequestAttribute User user) {
        exerciseService.register(request, user);
    }

    @PostMapping("/upload/video/{exerciseId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void videoRegister(@RequestPart(value = "file", required = false) MultipartFile file,
                              @PathVariable(name = "exerciseId") Long exerciseId) {
        exerciseService.videoUpload(exerciseId, file);
    }

    @CheckToken
    @GetMapping("/get/{exerciseId}")
    public Exercise getExerciseById(@PathVariable(name = "exerciseId") Long exerciseId, @RequestAttribute User user) {
        return exerciseService.getExerciseById(exerciseId, user);
    }

    @CheckToken
    @GetMapping("/get/list")
    public List<Exercise> getExerciseList(@RequestAttribute User user) {
        return exerciseService.getExcrciseList(user);
    }

    @GetMapping("/ranking")
    public List<ExerciseRankingInterface> getRanking(@RequestParam(name = "exerciseType") ExerciseType exerciseType) {
        return exerciseService.ranking(exerciseType);
    }
}
