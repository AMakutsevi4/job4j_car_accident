package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accident = new ConcurrentHashMap<>();
    private final AtomicInteger size = new AtomicInteger(0);
    private final List<AccidentType> types = new ArrayList<>();
    private final List<Rule> rules = new ArrayList<>();

    private AccidentMem() {
        types.add(AccidentType.of(0, "Предупреждение"));
        types.add(AccidentType.of(1, "Штраф"));
        types.add(AccidentType.of(2, "Лишение прав"));

        rules.add(Rule.of(0, "Статья. 1"));
        rules.add(Rule.of(1, "Статья. 2"));
        rules.add(Rule.of(2, "Статья. 3"));
    }

    public Collection<Accident> findAll() {
        return accident.values();
    }

    public void create(Accident accident, String[] ids) {
        Set<Rule> rule = new HashSet<>();
        for (String id : ids) {
            rule.add(rules.get(Integer.parseInt(id)));
        }
        accident.setRules(rule);
        accident.setId(size.incrementAndGet());
        accident.setType(findTypeId(accident.getType().getId()));
        this.accident.put(size.get(), accident);
    }

    public void update(Accident accident, String[] ids) {
        Set<Rule> rule = new HashSet<>();
        for (String id : ids) {
            rule.add(rules.get(Integer.parseInt(id)));
        }
        accident.setRules(rule);
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
        return this.types.get(id);
    }

    public Collection<Rule> getAllRule() {
        return this.rules;
    }
}