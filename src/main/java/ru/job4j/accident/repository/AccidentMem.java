package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accident = new ConcurrentHashMap<>();
    private final AtomicInteger size = new AtomicInteger(0);
    private final List<AccidentType> types = new ArrayList<>();

    private AccidentMem() {
        types.add(AccidentType.of(1, "Предупреждение"));
        types.add(AccidentType.of(2, "Штраф"));
        types.add(AccidentType.of(3, "Лишение прав"));
    }

    public Collection<Accident> findAll() {
        return accident.values();
    }

    public void create(Accident accident) {
        accident.setId(size.incrementAndGet());
        accident.setType(findTypeId(accident.getType().getId()));
        this.accident.put(size.get(), accident);

    }

    public void update(Accident accident) {
        accident.setType(findTypeId(accident.getType().getId()));
        this.accident.put(accident.getId(), accident);
    }

    public Accident get(int id) {
        return this.accident.get(id);
    }

    public Collection<AccidentType> getAllType() {
        return this.types;
    }
    public AccidentType findTypeId(int id) {
        return this.types.get(--id);
    }
}
