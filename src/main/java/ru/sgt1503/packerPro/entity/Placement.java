package ru.sgt1503.packerPro.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * todo Document type Placement
 */
@Data
@Getter
@Setter
@Entity
@Table(name = "placement")
public class Placement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<Container> containers;

    public Placement(List<Container> containers) {
        this.containers = containers;
    }

    public Placement() {

    }

    public Placement(long id, List<Container> containers) {
        this.id = id;
        this.containers = containers;
    }
}
