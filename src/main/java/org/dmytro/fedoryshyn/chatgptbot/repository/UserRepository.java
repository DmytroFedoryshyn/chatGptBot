package org.dmytro.fedoryshyn.chatgptbot.repository;

import java.util.Optional;
import org.dmytro.fedoryshyn.chatgptbot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByLogin(String login);
}
