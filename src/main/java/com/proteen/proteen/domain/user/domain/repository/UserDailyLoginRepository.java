package com.proteen.proteen.domain.user.domain.repository;

import com.proteen.proteen.domain.user.domain.UserDailyLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDailyLoginRepository extends JpaRepository<UserDailyLogin, Long> {
}
