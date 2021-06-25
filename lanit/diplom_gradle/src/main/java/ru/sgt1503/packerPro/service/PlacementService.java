package ru.sgt1503.packerPro.service;

import org.springframework.stereotype.Service;
import ru.sgt1503.packerPro.entity.Container;
import ru.sgt1503.packerPro.entity.Placement;

import java.util.Set;

@Service
public interface PlacementService {
    Placement createPlacement(Placement placement);
}
