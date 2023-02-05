package es.geeko.web.controller;

import es.geeko.model.Usuario;
import es.geeko.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    // Go to Registration Page
    @GetMapping("/usuarios/crearcuenta")
    public String register() {
        return "/usuarios/crearcuenta";
    }

    // Read Form data to save into DB
    @PostMapping("/saveUser")
    public String saveUser(
            @ModelAttribute Usuario user,
            Model model
    )
    {
        Integer id = userService.saveUser(user);
        String message = "User '"+id+"' saved successfully !";
        model.addAttribute("msg", message);
        return "/usuarios/crearcuenta";
    }
}