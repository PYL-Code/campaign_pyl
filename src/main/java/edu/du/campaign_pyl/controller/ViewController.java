package edu.du.campaign_pyl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/{path:^(?!api).*$}")
    public String redirect() {
        return "forward:/index.html";
    }
}