package kpi.spring2022.labs.first_lab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplatesController {

    @GetMapping("/")
    public String getMainTemplate() {
        return "team";
    }

    @GetMapping("/vladyslav")
    public String getVladInfoTemplate() {
        return "vladyslav";
    }

    @GetMapping("/bogdan")
    public String getBogdanInfoTemplate() {
        return "bogdan";
    }
}
