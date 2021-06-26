package ru.sgt1503.packerPro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.sgt1503.packerPro.entity.Container;
import ru.sgt1503.packerPro.entity.Thing;
import ru.sgt1503.packerPro.service.*;

@Configuration
@ComponentScan("ru.sgt1503.packerPro.service")
public class AppConfig {
    @Bean
    public Container createAnyContainerMethodFactory() {
        return GetAnyContainerFromFactory.createAnyContainer();
    }

    @Bean
    public Thing createAnyThingMethodFactory() {
        return GetAnyThingFromFactory.createAnyThing();
    }

}
