package ru.sgt1503.packerPro.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.sgt1503.packerPro.entity.Container;
import ru.sgt1503.packerPro.entity.Thing;
import ru.sgt1503.packerPro.service.ContainerService;
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

    @Autowired
    public Algorithm(ContainerService containerService, ThingService thingService) {
        this.containerService = containerService;
        this.thingService = thingService;
        resolve();
    }


    public void resolve(){
        generateNFA();
    }

    public void generateNFA(){
        //ArrayList это массив, который умеет сам расширяться
        //коробки внесенные в бд
        ArrayList<Thing> things = (ArrayList<Thing>) thingService.getAll();
        List<Thing> emptyListOfThings ;
        //контейнеры
        ArrayList<Container> containers = (ArrayList<Container>) containerService.getAll();
        ArrayList<Container> containersResolved = (ArrayList<Container>) containerService.getAll();
        for (int i = 0; i < containers.size(); i++) {
            int randomThing = (int) ((Math.random() * (things.size())));
            Thing thing = things.get(randomThing);
            double x;
            double y;
            double z;
            thing.setPosition();
        }
    }
}
