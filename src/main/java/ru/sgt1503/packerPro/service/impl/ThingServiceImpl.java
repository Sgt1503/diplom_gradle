package ru.sgt1503.packerPro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgt1503.packerPro.entity.Container;
import ru.sgt1503.packerPro.entity.Thing;
import ru.sgt1503.packerPro.repo.ContainerRepository;
import ru.sgt1503.packerPro.repo.ThingRepository;
import ru.sgt1503.packerPro.service.ThingService;

import java.util.ArrayList;
import java.util.List;

/**
 * todo Document type ThingServiceImpl
 */
@Service
public class ThingServiceImpl implements ThingService {

    private final ThingRepository thingRepository;

    @Autowired
    public ThingServiceImpl(ThingRepository thingRepository) {
        this.thingRepository = thingRepository;
    }

    @Override
    public Thing createThing(Thing thing) {
        return thingRepository.save(thing);
    }

    @Override
    public Thing getThing(Long id) {
        return thingRepository.findById(id).orElse(null);
    }

    @Override
    public Thing editThing(Thing thing) {
        return thingRepository.save(thing);
    }

    @Override
    public void deleteThing(Thing thing) {
        thingRepository.delete(thing);
    }

    @Override
    public void deleteThing(Long id) {
        thingRepository.deleteById(id);
    }

    @Override
    public Long getTableSize() {
        return thingRepository.count();
    }

    @Override
    public List<Thing> getAll() {
        List<Thing> things = new ArrayList<>();
        thingRepository.findAll().forEach(things::add);
        return things;
    }
}
