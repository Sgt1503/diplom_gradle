package ru.raidbot10lvlfarmer.DB.Service;

import org.springframework.stereotype.Service;
import ru.raidbot10lvlfarmer.DB.Entity.BotFollower;

import java.util.List;

@Service
public interface BotFollowerService {
    BotFollower addFollower(BotFollower follower);
    BotFollower getFollowerByUserName(String username);
    List<BotFollower> getAllFollowers();
}
