package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Accident> getAll() {
        return jdbc.query("select id, name, text, address, type_id, rule_id from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(findTypeById(rs.getInt("type_id")));
                    accident.setRules(findRuleById(rs.getInt("rule_id")));
                    return accident;
                });
    }

    public AccidentType findTypeById(int id) {
        return jdbc.queryForObject("select * from accident_type where id = ?",
                (rs, row) -> AccidentType.of(
                        rs.getInt("id"),
                        rs.getString("name")
                ),
                id);
    }
    public Set<Rule> findRuleById(int id) {
        return new HashSet<>(jdbc.query("select * from accident_rule where id = ?",
            (rs, row) -> {
            Rule rule = new Rule();
            rule.setId(rs.getInt("id"));
            rule.setName(rs.getString("name"));
            return rule;
        }, id));
    }

    public Collection<AccidentType> getAllType() {
        return jdbc.query("select * from accident_type",
            (rs, row) -> {
            AccidentType type = new AccidentType();
            type.setId(rs.getInt("id"));
            type.setName(rs.getString("Name"));
            return type;
        });
    }

    public Collection<Rule> getAllRule() {
        return jdbc.query("select * from accident_rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public void create(Accident accident, String[] ids) {
        jdbc.update("insert into accident (name, text, address,type_id, rule_id) values (?, ?, ?, ?, ?)",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getRules()
        );
    }

    public Accident get(int id) {
        return jdbc.queryForObject("select * from accident where id = ?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));

                   return accident;
                }, id);
    }

    public void update(Accident accident, String[] ids) {
        jdbc.update("update accident set name = ?, text = ?, address = ?, type_id = ?, rule_id = ? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getRules(),
                accident.getId()
        );
    }
}