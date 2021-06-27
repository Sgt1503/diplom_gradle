package ru.sgt1503.packerPro.Resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sgt1503.packerPro.entity.Chromosome;
import ru.sgt1503.packerPro.entity.Container;
import ru.sgt1503.packerPro.entity.Placement;
import ru.sgt1503.packerPro.entity.Thing;
import ru.sgt1503.packerPro.service.ChromosomeService;
import ru.sgt1503.packerPro.service.ContainerService;
import ru.sgt1503.packerPro.service.PlacementService;
import ru.sgt1503.packerPro.service.ThingService;

import java.util.*;

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
    }


    public void resolve(){
    chromosomeService.deleteAll();
        for (int i = 0; i < 8; i++) {
            generateNFA();
        }
        geneCrossing(getTwoParents());
    }

    private void geneCrossing(List<Chromosome> twoParents) {
        List<Container> listOfAllContainers = containerService.getAll();
        List<Container> pr1 = twoParents.get(0).getPlacement().getContainers();
        List<Container> pr2 = twoParents.get(1).getPlacement().getContainers();
        List<Container> child1 = new ArrayList<>();
        List<Container> child2 = new ArrayList<>();
        Placement mutatedPlacement1 = new Placement();
        Placement mutatedPlacement2 = new Placement();
        List<Thing> first20PercentsOfThings = new ArrayList<>();
        List<Thing> last80PercentsOfThings = new ArrayList<>();
        int countOfThings = 0;
        for (int j = 0; j < pr1.size(); j++) {
            countOfThings += pr1.get(j).getThings().size();
        }

        int f20P = (int) (countOfThings * 0.2);

        List<Thing> thingsIterable = new ArrayList<>();
        for (int l = 0; l < pr1.size(); l++) {
            thingsIterable.addAll(pr1.get(l).getThings());
        }

        for (int h = 0; h < f20P; h++) {
            first20PercentsOfThings.add(thingsIterable.get(h));
        }
        child1.addAll(generateNewAccommodation(first20PercentsOfThings,listOfAllContainers).getPlacement().getContainers());

        int l80P = (int) (countOfThings * 0.8);

        List<Thing> thingsIterable1 = new ArrayList<>();
        for (int l = pr2.size(); l >= f20P; l--) {
            thingsIterable1.addAll(pr2.get(l).getThings());
        }

        for (int h = 0; h < l80P; h++) {
            last80PercentsOfThings.add(thingsIterable1.get(h));
        }
        Chromosome intermediate = generateNewAccommodation(last80PercentsOfThings,child1);
        child1.addAll(intermediate.getPlacement().getContainers());
        mutatedPlacement1.setContainers(child1);
        Chromosome mutatedChromosome1 = new Chromosome(mutatedPlacement1, intermediate.getUsedSpace());


        List<Thing> first20PercentsOfThingsOf2Child = new ArrayList<>();
        List<Thing> last80PercentsOfThingsOf2Child  = new ArrayList<>();

        List<Thing> thingsIterable2 = new ArrayList<>();
        for (int l = 0; l < pr2.size(); l++) {
            thingsIterable2.addAll(pr2.get(l).getThings());
        }

        for (int h = 0; h < f20P; h++) {
            first20PercentsOfThingsOf2Child.add(thingsIterable2.get(h));
        }
        child2.addAll(generateNewAccommodation(first20PercentsOfThingsOf2Child,listOfAllContainers).getPlacement().getContainers());


        List<Thing> thingsIterable3 = new ArrayList<>();
        for (int l = pr2.size(); l >= f20P; l--) {
            thingsIterable3.addAll(pr1.get(l).getThings());
        }

        for (int h = 0; h < l80P; h++) {
            last80PercentsOfThingsOf2Child.add(thingsIterable3.get(h));
        }
        Chromosome intermediate1 = generateNewAccommodation(last80PercentsOfThingsOf2Child,child2);
        child2.addAll(intermediate.getPlacement().getContainers());
        mutatedPlacement2.setContainers(child2);
        Chromosome mutatedChromosome2 = new Chromosome(mutatedPlacement2, intermediate1.getUsedSpace());

        chromosomeService.createChromosome(mutatedChromosome1);
        chromosomeService.createChromosome(mutatedChromosome2);
        List<Chromosome> chromosomes = chromosomeService.getEightChromosomes();
        Collections.sort(chromosomes, (o1, o2) -> String.valueOf((int)o1.getUsedSpace()).compareTo(String.valueOf((int)o2.getUsedSpace())));
        while (chromosomes.size() > 8)
            chromosomes.remove(chromosomes.size());
    }

    public Chromosome generateNewAccommodation(List<Thing> things1, List<Container> containers1){
        List<Thing> things = things1;
        List<Container> containers = containers1;
        double totalUsedSpace = 0;
        for (int z = 0; z < containers.size(); z++) {
            containers.get(z).getThings().clear();
        }
        for (int i = 0; i < containers.size(); i++) {
            Container container = containers.get(i);
            double usedSpace = 0l;
            double volume = container.getHeight() * container.getWidth() * container.getLength();
            ArrayList position = new ArrayList();
            if (things.size() == 0){
                break;
            }
            for (int j = 0; j < things.size(); j++) {
                int placedThingsCounter = 0;
                int j1 = (int) (Math.random() * things.size());
                Thing thing = things.get(j1);
                things.remove(thing);
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
                    while (placedThingsCounter < notPlacedThings) {
                        x = Math.random() * (container.getWidth() - thing.getWidth());
                        y = Math.random() * (container.getLength() - thing.getLength());
                        z = Math.random() * (container.getHeight() - thing.getHeight());


                        for (int k = 0; k < things.size(); k++) {
                            Thing thing1 = things.get(k);
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
                position.clear();
                totalUsedSpace += usedSpace;
            }
        }
        Placement placement = new Placement(containers);
        Chromosome chromosome = new Chromosome(placement, totalUsedSpace);
        return chromosome;
    }

    public void generateNFA(){
        //ArrayList это массив, который умеет сам расширяться
        //коробки внесенные в бд
        List<Thing> things = thingService.getAll();
        //контейнеры
        List<Container> containers = containerService.getAll();
        double totalUsedSpace = 0;
        int usedContainers = 0;

        for (int i = 0; i < containers.size(); i++) {
            Container container = containers.get(i);
            double usedSpace = 0l;
            double volume = container.getHeight() * container.getWidth() * container.getLength();
            ArrayList position = new ArrayList();
            if (things.size() == 0){
            break;
            }
            usedContainers++;
            for (int j = 0; j < things.size(); j++) {
                int placedThingsCounter = 0;
                int j1 = (int) (Math.random() * things.size());
                Thing thing = things.get(j1);
                things.remove(thing);
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
                    while (placedThingsCounter < notPlacedThings) {
                        x = Math.random() * (container.getWidth() - thing.getWidth());
                        y = Math.random() * (container.getLength() - thing.getLength());
                        z = Math.random() * (container.getHeight() - thing.getHeight());


                        for (int k = 0; k < things.size(); k++) {
                            Thing thing1 = things.get(k);
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
                position.clear();
                totalUsedSpace += usedSpace;
            }
        }
        Placement placement = new Placement(containers);
        placementService.createPlacement(placement);
        Chromosome chromosome = new Chromosome(placement, totalUsedSpace);
        chromosomeService.createChromosome(chromosome);
    }

    private List<Chromosome> getTwoParents() {
        List<Chromosome> chromosomes = chromosomeService.getEightChromosomes();
        int p1 = 0;
        int p2 = 0;
        while (p1 == p2)
        {
            p1 = (int) Math.random() * chromosomes.size();
            p2 = (int) Math.random() * chromosomes.size();
        }

        Chromosome parent1 = chromosomes.get(p1);
        Chromosome parent2 = chromosomes.get(p2);
        List<Chromosome> parents = new ArrayList<>();
        parents.add(parent1);
        parents.add(parent2);
        return parents;
    }

}
