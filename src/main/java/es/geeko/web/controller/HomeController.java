package es.geeko.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getPage() {
        return "index";
    }

    @GetMapping("/usuarios/login")
    public String getLoginPage() {
        return "/usuarios/login";
    }

    @GetMapping("/logout")
    public String getLogoutPage() {
        return "logout";
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "inicio";
    }

    @GetMapping("/welcome")
    public String getWelcomePage() {
        return "index";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "adminPage";
    }

    @GetMapping("/common")
    public String getCommonPage() {
        return "commonPage";
    }

    @GetMapping("/accessDenied")
    public String getAccessDeniedPage() {
        return "accessDeniedPage";
    }
}