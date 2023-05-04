package com.proteen.proteen.domain.exercise.domain.repository;

import com.proteen.proteen.domain.exercise.domain.Exercise;
import com.proteen.proteen.domain.exercise.persentation.dto.response.ExerciseRankingInterface;
import com.proteen.proteen.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByExerciseIdAndUser(Long exerciseId, User user);
    List<Exercise> findAllByUser(User user);

    @Query(value = "select avg(e.score) as avg_score, u.name, sum(coalesce(e.calorie, 0)) as total_calories " +
            "from exercise e " +
            "inner join user u on e.user_Id = u.user_id " +
            "GROUP BY u.user_id " +
            "ORDER BY avg(e.score) desc",  nativeQuery = true)
    List<ExerciseRankingInterface> findAllByRanking();
}
