package ru.sgt1503.packerPro.service;

import org.springframework.stereotype.Service;
import ru.sgt1503.packerPro.entity.Thing;

import java.util.List;

@Service
public interface ThingService {
    Thing createThing(Thing thing);
    Thing getThing(Long id);
    Thing editThing(Thing thing);
    void deleteThing(Thing thing);
    void deleteThing(Long id);
    Long getTableSize();
    List<Thing> getAll();
}
