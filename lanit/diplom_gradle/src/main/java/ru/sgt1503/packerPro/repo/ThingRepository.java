package ru.sgt1503.packerPro.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sgt1503.packerPro.entity.Thing;

@Repository
public interface ThingRepository extends CrudRepository<Thing, Long> {
}