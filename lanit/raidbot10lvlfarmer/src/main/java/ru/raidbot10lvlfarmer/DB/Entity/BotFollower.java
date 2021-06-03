package ru.sgt1503.red_roses_bot.DB.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Table(name = "BotFollower")
public class BotFollower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String userID;

    @Column
    private Long abobaCounter;

    @Column
    private String steamID;

    @Column
    private Long messageCounter;

    public BotFollower(String username, String userID, Long abobaCounter, String steamID, Long messageCounter) {
        this.username = username;
        this.userID = userID;
        this.abobaCounter = abobaCounter;
        this.steamID = steamID;
        this.messageCounter = messageCounter;
    }



    public BotFollower() {
    }
}
