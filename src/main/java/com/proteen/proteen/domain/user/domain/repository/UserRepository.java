package com.proteen.proteen.domain.user.domain.repository;

import com.proteen.proteen.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(String id);

    boolean existsById(String id);

    boolean existsByName(String name);
}
