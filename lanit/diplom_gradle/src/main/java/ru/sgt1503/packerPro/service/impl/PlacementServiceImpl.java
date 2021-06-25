package ru.sgt1503.packerPro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgt1503.packerPro.entity.Container;
import ru.sgt1503.packerPro.entity.Placement;
import ru.sgt1503.packerPro.repo.PlacementRepository;
import ru.sgt1503.packerPro.service.PlacementService;

import java.util.Set;

/**
 * todo Document type PlacementServiceImpl
 */
@Service
public class PlacementServiceImpl implements PlacementService {
    private final PlacementRepository placementRepository;

    @Autowired
    public PlacementServiceImpl(PlacementRepository placementRepository) {
        this.placementRepository = placementRepository;
    }

    @Override
    public Placement createPlacement(Placement placement) {
        return placementRepository.save(placement);
    }
}
