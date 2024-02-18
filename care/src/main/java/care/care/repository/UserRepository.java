package care.care.repository;

import care.care.domain.User;


import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User User);
    Optional<User> findByid(Long id);
    Optional<User> findByName(String name);
    List<User> findAll();
}
