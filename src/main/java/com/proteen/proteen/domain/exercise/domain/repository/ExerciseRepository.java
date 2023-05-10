package com.proteen.proteen.domain.exercise.domain.repository;

import com.proteen.proteen.domain.exercise.domain.Exercise;
import com.proteen.proteen.domain.exercise.persentation.dto.response.ExerciseRankingInterface;
import com.proteen.proteen.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Optional<Exercise> findByExerciseIdAndUser(Long exerciseId, User user);
    List<Exercise> findAllByUser(User user);

    List<Exercise> findAll();

    @Query(value = "select avg(e.score) as avg_score, u.name, " +
            "sum(e.calorie) as total_calories, " +
            "sum(coalesce(e.time, 0)) as total_time, " +
            "(SELECT TIMESTAMPDIFF(DAY, MAX(b.Date), CAST(UTC_TIMESTAMP() AS DATE)) as attendance    " +
            "FROM DateHelper b " +
            "LEFT OUTER JOIN user_daily_login a ON a.Date = b.Date AND a.User_Id = u.user_id " +
            "WHERE b.Date <= CAST(UTC_TIMESTAMP() AS DATE) AND a.Date IS NULL) as attendance " +
            "from exercise e " +
            "inner join user u on e.user_Id = u.user_id " +
            "WHERE e.exercise_type = :type " +
            "GROUP BY u.user_id " +
            "ORDER BY avg(e.score) desc",  nativeQuery = true)
    List<ExerciseRankingInterface> findAllByRanking(@Param("type") String exerciseType);
}