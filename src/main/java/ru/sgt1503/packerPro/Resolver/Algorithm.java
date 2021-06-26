package ru.sgt1503.packerPro.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.sgt1503.packerPro.entity.Chromosome;
import ru.sgt1503.packerPro.entity.Container;
import ru.sgt1503.packerPro.entity.Placement;
import ru.sgt1503.packerPro.entity.Thing;
import ru.sgt1503.packerPro.service.ChromosomeService;
import ru.sgt1503.packerPro.service.ContainerService;
import ru.sgt1503.packerPro.service.PlacementService;
import ru.sgt1503.packerPro.service.ThingService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * todo Document type Algorithm
 */
@Component
public class Algorithm {
    private final ContainerService containerService;
    private final ThingService thingService;
    private final PlacementService placementService;
    private final ChromosomeService chromosomeService;

    @Autowired
    public Algorithm(ContainerService containerService, ThingService thingService, PlacementService placementService,ChromosomeService chromosomeService) {
        this.containerService = containerService;
        this.thingService = thingService;
        this.chromosomeService = chromosomeService;
        this.placementService = placementService;
        resolve();
    }


    public void resolve(){
        generateNFA();
    }

    public void generateNFA(){
        //ArrayList это массив, который умеет сам расширяться
        //коробки внесенные в бд
        List<Thing> things = thingService.getAll();
        //контейнеры
        List<Container> containers = containerService.getAll();
        double totalUsedSpace = 0l;
        int usedContainers = 0;

        for (int i = 0; i < containers.size(); i++) {
            Container container = containers.get(i);
            double usedSpace = 0l;
            double volume = container.getHeight() * container.getWidth() * container.getLength();
            int placedThingsCounter = 0;
            ArrayList position = new ArrayList();
            if (things.size() == 0){
            break;
            }
            usedContainers++;
            for (int j = 0; j < things.size(); j++) {
                int j1 = (int) (Math.random() * things.size());
                Thing thing = things.get(j1);
                int notPlacedThings = things.size();
                double x;
                double y;
                double z;
                double defautx = 0l;
                double defauty = 0l;
                double defautz = 0l;
                if (container.getThings().size() == 0){
                    usedSpace += thing.getHeight() * thing.getLength() * thing.getWidth();
                    position.add(defautx);
                    position.add(defauty);
                    position.add(defautz);
                }
                if (container.getThings().size() > 0) {
                    while (placedThingsCounter <= notPlacedThings) {
                        x = Math.random() * (container.getWidth() - thing.getWidth());
                        y = Math.random() * (container.getLength() - thing.getLength());
                        z = Math.random() * (container.getHeight() - thing.getHeight());


                        for (int k = 0; k < container.getThings().size(); k++) {
                            Thing thing1 = container.getThings().get(k);
                            if (thing1.getPosition().size() != 0) {
                                if (x > thing1.getPosition().get(0) + thing1.getWidth() || x + thing.getWidth() < thing1.getPosition().get(0)
                                        &&
                                        y > thing1.getPosition().get(1) + thing1.getLength() || y + thing.getLength() < thing1.getPosition().get(1)
                                        &&
                                        z > thing1.getPosition().get(2) + thing1.getHeight() || z + thing.getHeight() < thing1.getPosition().get(2)
                                        && volume >= usedSpace
                                ) {
                                    placedThingsCounter++;
                                }

                            }
                            placedThingsCounter++;
                            if (placedThingsCounter == notPlacedThings) {
                                position.add(x);
                                position.add(y);
                                position.add(z);
                                usedSpace += thing.getHeight() * thing.getLength() * thing.getWidth();
                            }
                        }

                    }
                }

                thing.setPosition(position);
                thing.setContainer(container);
                container.getThings().add(thing);
                things.remove(thing);
                position.clear();
            }
            totalUsedSpace += usedSpace;
        }
        Placement placement = new Placement(containers);
//        placementService.createPlacement(placement);
        Chromosome chromosome = new Chromosome(placement, totalUsedSpace);
//        chromosomeService.createChromosome(chromosome);
    }
}
