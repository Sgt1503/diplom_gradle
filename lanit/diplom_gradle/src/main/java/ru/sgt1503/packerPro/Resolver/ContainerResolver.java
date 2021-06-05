package ru.sgt1503.packerPro.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sgt1503.packerPro.entity.Container;
import ru.sgt1503.packerPro.entity.Thing;
import ru.sgt1503.packerPro.service.ContainerService;
import ru.sgt1503.packerPro.service.ThingService;

import java.util.ArrayList;
import java.util.List;

/**
 * todo Document type ContainerResolver
 */
@Component
public class ContainerResolver {
    private final ContainerService containerService;
    private final ThingService thingService;

    @Autowired
    public ContainerResolver(ContainerService containerService, ThingService thingService) {
        this.containerService = containerService;
        this.thingService = thingService;
    }

    public void put(Thing thing, Container container) {
        List<Thing> things = thingService.getAll();
        ArrayList<Double> position = new ArrayList<>(3);
        boolean NoCollision = true;
        double maxH = container.getHeight();
        double maxL = container.getLength();
        double maxW = container.getWidth();

        double HTh = thing.getHeight();
        double LTh = thing.getLength();
        double WTh = thing.getWidth();

        while (NoCollision) {
            for (Thing value : things) {
                position.add(0, Math.random() * (maxH));
                position.add(1, Math.random() * (maxL));
                position.add(2, Math.random() * (maxW));

                Double x = position.get(0);
                Double y = position.get(1);
                Double z = position.get(2);

                Double xT = value.getPosition().get(0);
                Double yT = value.getPosition().get(1);
                Double zT = value.getPosition().get(2);

                if (
                    (x > xT && x + HTh > xT) &&
                        (y > yT && x + LTh > yT) &&
                        (z > zT && z + WTh > zT)
                        &&
                        (x <= maxH - HTh) &&
                        (y <= maxL - LTh) &&
                        (z <= maxW - WTh)
                ) {
                    Thing thingAddedPosition = new Thing(
                        thing.getName(),
                        thing.getWidth(),
                        thing.getLength(),
                        thing.getHeight(),
                        position, container);
                    thingService.editThing(thingAddedPosition);
                    NoCollision = true;
                } else
                    NoCollision = false;
            }

        }
    }

    public void solvePackingProblem(){
        List<Thing> things = thingService.getAll();
        List<Container> containers = containerService.getAll();
        for (Container container : containers) {
            double heightC = container.getHeight();
            double widthC = container.getWidth();
            double lengthC = container.getLength();
            double volume = heightC * widthC * lengthC;
            double usedVolume = 0.0;
            boolean notFull = true;
            int j = 0;
            while (notFull && j <= 2) {
                Thing thing = things.get(j);
                double thingVolume = thing.getHeight() * thing.getWidth() * thing.getLength();
                usedVolume += thingVolume;
                if (usedVolume <= volume)
                    put(thing, container);
                else
                    notFull = false;
                j++;
                System.out.println(thing);
            }
        }

    }
}

