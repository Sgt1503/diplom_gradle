package ru.sgt1503.packerPro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgt1503.packerPro.entity.Container;
import ru.sgt1503.packerPro.repo.ContainerRepository;
import ru.sgt1503.packerPro.service.ContainerService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * todo Document type ContainerServiceImpl
 */
@Service
public class ContainerServiceImpl implements ContainerService {


    private final ContainerRepository containerRepository;

    @Autowired
    public ContainerServiceImpl(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    @Override
    public Container createContainer(Container container) {
        return containerRepository.save(container);
    }

    @Override
    public Container getContainer(Long id) {
        return containerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Container> getAll() {
        List<Container> containers = new ArrayList<>();
        containerRepository.findAll().forEach(containers::add);
        return containers;
    }

    @Override
    public List<String> getAllNames() {
        List<String> names = new ArrayList<>();
        containerRepository.findAll().iterator().forEachRemaining(container -> names.add(container.getName()));
        return names;
    }

    @Override
    public Container getContainerByName(String name) { return containerRepository.findContainerByName(name); }

    @Override
    public Container editContainer(Container container) {
        return containerRepository.save(container);
    }

    @Override
    public Container editByNameContainer(String name) {
        return containerRepository.findContainerByName(name);
    }

    @Override
    public void deleteContainer(Container container) {
        containerRepository.delete(container);
    }

    @Override
    public void deleteContainer(Long id) {
        containerRepository.deleteById(id);
    }

    @Override
    public Long getTableSize() {
        return containerRepository.count();
    }
}
