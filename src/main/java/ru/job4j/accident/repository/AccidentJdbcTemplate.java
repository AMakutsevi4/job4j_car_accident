package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.List;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Accident> getAll() {
        return jdbc.query("select id, name from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                });
    }

    public Collection<AccidentType> getAllType() {
        return jdbc.query("select id, name from accident_type",
            (rs, row) -> {
            AccidentType type = new AccidentType();
            type.setId(rs.getInt("id"));
            type.setName(rs.getString("Name"));
            return type;
        });
    }

    public Collection<Rule> getAllRule() {
        return jdbc.query("select id, name from accident_rule",
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
                accident.getType(),
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
                accident.getType(),
                accident.getRules(),
                accident.getId()
        );
    }

}
