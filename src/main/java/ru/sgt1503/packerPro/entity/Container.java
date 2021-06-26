package ru.sgt1503.packerPro.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * todo Document type containerEntity
 */

@Data
@Getter
@Setter
@Entity
@Table(name = "Container")
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, nullable = false)
    @NotEmpty
    private String name;

    @Column
    private double width;

    @Column
    private double length;

    @Column
    private double height;

    @OneToMany(mappedBy = "container", fetch = FetchType.EAGER)
    private List<Thing> things = new ArrayList<>();

    public Container(String name, double width, double length, double height) {
        this.name = name;
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public Container() {}

    @Override
    public String toString() {
        return this.name;
    }
}