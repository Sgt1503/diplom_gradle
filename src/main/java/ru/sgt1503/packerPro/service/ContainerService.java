package ru.sgt1503.packerPro.service;

import org.springframework.stereotype.Service;
import ru.sgt1503.packerPro.entity.Container;
import java.util.List;

@Service
public interface ContainerService {
    Container createContainer(Container container);
    Container getContainer(Long id);
    List<Container> getAll();
    List<String> getAllNames();
    Container getContainerByName(String name);
    Container editContainer(Container container);
    Container editByNameContainer(String name);
    void deleteContainer(Container container);
    void deleteContainer(Long id);
    Long getTableSize();
}
