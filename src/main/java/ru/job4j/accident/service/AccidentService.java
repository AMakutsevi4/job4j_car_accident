package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {

    private final AccidentMem memService;

    public AccidentService(AccidentMem memService) {
        this.memService = memService;
    }

    public Collection<Accident> findAll() {
        return memService.findAll();
    }

    public void create(Accident accident) {
        memService.create(accident);
    }

    public void update(Accident accident) {
        memService.update(accident);
    }
}