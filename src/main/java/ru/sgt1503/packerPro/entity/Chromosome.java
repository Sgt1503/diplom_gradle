package ru.sgt1503.packerPro.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * todo Document type Placement
 */
@Data
@Getter
@Setter
@Entity
@Table(name = "chromosome")
public class Chromosome {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Placement placement;

    @Column
    private double usedSpace;

    public Chromosome(Placement placement, double usedSpace) {
        this.placement = placement;
        this.usedSpace = usedSpace;
    }

    public Chromosome() {

    }
}
