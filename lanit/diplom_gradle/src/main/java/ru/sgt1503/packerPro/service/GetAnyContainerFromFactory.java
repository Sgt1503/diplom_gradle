package ru.sgt1503.packerPro.service;

import ru.sgt1503.packerPro.entity.Container;

public class GetAnyContainerFromFactory {
    public static Container createAnyContainer(String name,double width, double length, double height){
        return new Container(name,width,length,height);
    }

    public static Container createAnyContainer() {
        return new Container();
    }
}
