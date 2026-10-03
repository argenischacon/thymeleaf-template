package org.example.thymeleaftemplate.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserDashboardController {

    @GetMapping
    public String root() {
        return "redirect:/user/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "user/dashboard";
    }
}
