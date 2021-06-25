package ru.raidbot10lvlfarmer.DB.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.raidbot10lvlfarmer.DB.Entity.BotFollower;
import ru.raidbot10lvlfarmer.DB.Repo.BotFollowerRepo;
import ru.raidbot10lvlfarmer.DB.Service.BotFollowerService;

import java.util.List;

@Service
public class BotFollowerServiceImpl implements BotFollowerService {
    private final BotFollowerRepo botFollowerRepo;

    @Autowired
    public BotFollowerServiceImpl(BotFollowerRepo botFollowerRepo) {
        this.botFollowerRepo = botFollowerRepo;
    }

    @Override
    public BotFollower addFollower(BotFollower follower) {
        return botFollowerRepo.save(follower);
    }

    @Override
    public BotFollower getFollowerByUserName(String username) {
        return botFollowerRepo.findByUsername(username);
    }

    @Override
    public List<BotFollower> getAllFollowers() {
        return botFollowerRepo.findAll();
    }

}
