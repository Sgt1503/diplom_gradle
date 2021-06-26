package ru.sgt1503.packerPro.service;

import org.springframework.stereotype.Service;
import ru.sgt1503.packerPro.entity.Chromosome;
import ru.sgt1503.packerPro.entity.Placement;

@Service
public interface ChromosomeService {
    Chromosome createChromosome(Chromosome chromosome);
    void deleteChromosomeByUsedSpace(Double usedSpace);
}
