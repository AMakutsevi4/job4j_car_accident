package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accident = new ConcurrentHashMap<>();
    private final AtomicInteger size = new AtomicInteger(1);

    private AccidentMem() {

    }

    public Collection<Accident> findAll() {
        return accident.values();
    }

    public void create(Accident accident) {
       this.accident.put(size.incrementAndGet(), accident);
   }

    public void update(Accident accident) {
        this.accident.put(accident.getId(), accident);
    }
}
