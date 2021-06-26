package ru.sgt1503.packerPro.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sgt1503.packerPro.entity.Chromosome;

@Repository
public interface ChromosomeRepository extends CrudRepository<Chromosome, Long> {
    Chromosome findChromosomeByUsedSpace(Double usedSpace);
    void deleteByUsedSpace(Double usedSpace);
}