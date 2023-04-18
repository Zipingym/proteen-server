package com.proteen.proteen.domain.exercise.persentation;

import com.proteen.proteen.domain.exercise.domain.Exercise;
import com.proteen.proteen.domain.exercise.persentation.dto.request.CreateRequest;
import com.proteen.proteen.domain.exercise.service.ExerciseService;
import com.proteen.proteen.domain.user.domain.User;
import com.proteen.proteen.global.annotation.CheckToken;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @CheckToken
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void register(@RequestBody CreateRequest request, @RequestAttribute User user) {
        exerciseService.register(request, user);
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
}