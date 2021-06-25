package ru.sgt1503.packerPro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgt1503.packerPro.entity.Chromosome;
import ru.sgt1503.packerPro.entity.Placement;
import ru.sgt1503.packerPro.repo.ChromosomeRepository;
import ru.sgt1503.packerPro.service.ChromosomeService;

/**
 * todo Document type ChromosomeServiceImpl
 */
@Service
public class ChromosomeServiceImpl implements ChromosomeService {
    private final ChromosomeRepository chromosomeRepository;

    @Autowired
    public ChromosomeServiceImpl(ChromosomeRepository chromosomeRepository) {
        this.chromosomeRepository = chromosomeRepository;
    }

    @Override
    public Chromosome createChromosome(Chromosome chromosome) {
        return chromosomeRepository.save(chromosome);
    }

    @Override
    public void deleteChromosomeByUsedSpace(Double usedSpace) {
        chromosomeRepository.deleteByUsedSpace(usedSpace);
    }
}
