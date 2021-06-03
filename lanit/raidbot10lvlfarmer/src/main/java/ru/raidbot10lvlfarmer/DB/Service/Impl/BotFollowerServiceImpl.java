package ru.sgt1503.red_roses_bot.DB.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgt1503.red_roses_bot.DB.Entity.BotFollower;
import ru.sgt1503.red_roses_bot.DB.Entity.Follower;
import ru.sgt1503.red_roses_bot.DB.Repo.BotFollowerRepo;
import ru.sgt1503.red_roses_bot.DB.Service.BotFollowerService;

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
    public void incrementAbobaCounterById(String id) {
        Long abobaCount = botFollowerRepo.findByUserID(id).getAbobaCounter();
        botFollowerRepo.findByUserID(id).setAbobaCounter(abobaCount+1);
    }

    @Override
    public BotFollower getFollowerById(Long id) {
        return botFollowerRepo.findById(id).orElse(null);
    }

    @Override
    public BotFollower getFollowerByUserName(String username) {
        return botFollowerRepo.findByUsername(username);
    }

    @Override
    public BotFollower getFollowerByUserId(String id) {
        return botFollowerRepo.findByUserID(id);
    }

    @Override
    public BotFollower editAbobaCounterOfFollower(BotFollower follower) {
        BotFollower followerEdited;
        Long newCount = follower.getAbobaCounter() + 1;
        followerEdited = new BotFollower(follower.getId(), follower.getUsername(), follower.getUserID(), newCount, follower.getSteamID(), follower.getMessageCounter());
        follower = followerEdited;
        return botFollowerRepo.save(follower);
    }

    @Override
    public BotFollower editMessageCounterOfFollower(BotFollower follower) {
        BotFollower followerEdited;
        Long newCount = follower.getMessageCounter() + 1;
        followerEdited = new BotFollower(follower.getId(), follower.getUsername(), follower.getUserID(), follower.getAbobaCounter(), follower.getSteamID(), newCount);
        follower = followerEdited;
        return botFollowerRepo.save(follower);
    }

    @Override
    public Boolean existByUserId(String id) {
        return botFollowerRepo.existsFollowerByUserID(id);
    }

    @Override
    public List<BotFollower> getAllFollowers() {
        return botFollowerRepo.findAll();
    }

    @Override
    public BotFollower getRandomFollower() {
        return getRandomFollower();
    }

    @Override
    public void cleanAbobaForAll() {
        botFollowerRepo.setZeroAbobaCounterForAll();
    }
}
