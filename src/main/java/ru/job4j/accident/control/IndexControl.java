package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", "Alexandr Makutsevich");
        model.addAttribute("numbers", counter());
        return "index";
    }

    private List<String> counter() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= 20; i++) {
            list.add("Number " + i);
        }
        return list;
    }
}
