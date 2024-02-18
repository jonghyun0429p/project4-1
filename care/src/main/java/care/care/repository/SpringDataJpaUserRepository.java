package care.care.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import care.care.domain.User;


public interface SpringDataJpaUserRepository extends JpaRepository<User, Long>, UserRepository {
    
    Optional<User> findByName(String name);
}
