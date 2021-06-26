package ru.sgt1503.packerPro.service;

import ru.sgt1503.packerPro.entity.Container;
import ru.sgt1503.packerPro.entity.Thing;

public class GetAnyThingFromFactory {
    public static Thing createAnyThing(String name,double width, double length, double height, Container container){
        return new Thing(name,width,length,height, container);
    }

    public static Thing createAnyThing() {
        return new Thing();
    }
}
