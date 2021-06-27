package ru.sgt1503.packerPro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgt1503.packerPro.entity.Chromosome;
import ru.sgt1503.packerPro.repo.ChromosomeRepository;
import ru.sgt1503.packerPro.service.ChromosomeService;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Chromosome> getEightChromosomes() {
        List<Chromosome> chromosomes = new ArrayList<>();
        Iterable<Chromosome> c = chromosomeRepository.findAll();
        c.iterator().forEachRemaining(chromosome -> chromosomes.add(chromosome));
        while (chromosomes.size() > 8){
            chromosomes.remove(0);}
        return chromosomes;
    }

    @Override
    public Chromosome getChromosome(Chromosome chromosome) {
        return chromosomeRepository.getChromosomeByUsedSpaceLessThan(Long.MAX_VALUE);
    }

    @Override
    public void deleteAll() {
        chromosomeRepository.deleteAll();
    }
}
