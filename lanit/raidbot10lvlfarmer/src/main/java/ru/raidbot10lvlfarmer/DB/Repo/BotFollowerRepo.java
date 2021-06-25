package ru.raidbot10lvlfarmer.DB.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.raidbot10lvlfarmer.DB.Entity.BotFollower;


@Repository
public interface BotFollowerRepo extends JpaRepository<BotFollower, Long> {
    BotFollower findByUsername(String username);
}
