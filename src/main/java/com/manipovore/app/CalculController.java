package com.manipovore.app;

import com.manipovore.calculator.Calcul;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalculController {

    @GetMapping("/calcul")
    public String calculForm(Model model) {
        model.addAttribute("calcul", new Calcul());
        return "calcul";
    }

    @PostMapping("/calcul")
    public String calculSubmit(@ModelAttribute Calcul c) {
        return "calcul";
    }
}
