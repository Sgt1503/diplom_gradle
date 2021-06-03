package ru.sgt1503.packerPro.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sgt1503.packerPro.entity.Container;

@Repository
public interface ContainerRepository extends CrudRepository<Container, Long> {
    Container findContainerByName(String name);
}