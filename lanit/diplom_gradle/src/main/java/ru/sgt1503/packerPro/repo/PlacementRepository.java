package ru.sgt1503.packerPro.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sgt1503.packerPro.entity.Placement;

@Repository
public interface PlacementRepository extends CrudRepository<Placement, Long> {
}