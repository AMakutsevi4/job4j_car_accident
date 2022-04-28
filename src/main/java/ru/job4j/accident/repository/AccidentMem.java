package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accident = new ConcurrentHashMap<>();

    private AccidentMem() {
        accident.put(1, new Accident(1, "С 888 СО 14", "Проезд на красный", "Нерюнгри"));
        accident.put(2, new Accident(2, "X 001 XР 14", "Парковка в неположенном месте", "Хабаровск"));
        accident.put(3, new Accident(3, "О 013 ОО 14", "Превышение скорости", "Москва"));
    }

    public Collection<Accident> findAll() {
        return accident.values();
    }
}
