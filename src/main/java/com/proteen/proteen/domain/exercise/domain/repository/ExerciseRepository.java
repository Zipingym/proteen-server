package com.proteen.proteen.domain.exercise.domain.repository;

import com.proteen.proteen.domain.exercise.domain.Exercise;
import com.proteen.proteen.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByExerciseIdAndUser(Long exerciseId, User user);
    Optional<List<Exercise>> findAllByUser(User user);
}
