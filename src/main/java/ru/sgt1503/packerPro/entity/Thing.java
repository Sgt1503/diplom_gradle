package ru.sgt1503.packerPro.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * todo Document type thingEntity
 */

@Data
@Getter
@Setter
@Entity
@Table(name = "Thing")
public class Thing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private double width;

    @Column
    private double length;

    @Column
    private double height;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Double> position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "container_id")
    private Container container;

    public Thing() {
    }

    public Thing(String name, double width, double length, double height, List<Double> position, Container container) {
        this.name = name;
        this.width = width;
        this.length = length;
        this.height = height;
        this.position = position;
        this.container = container;
    }

    public Thing(String name, double width, double length, double height, Container container) {
        this.name = name;
        this.width = width;
        this.length = length;
        this.height = height;
        this.container = container;
    }
}
