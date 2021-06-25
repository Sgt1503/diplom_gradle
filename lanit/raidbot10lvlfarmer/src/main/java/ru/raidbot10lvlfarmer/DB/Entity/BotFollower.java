package ru.raidbot10lvlfarmer.DB.Entity;

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
@Table(name = "fake_account")
public class BotFollower {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    public BotFollower(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public BotFollower() {
    }
}
