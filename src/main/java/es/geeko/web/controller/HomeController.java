package es.geeko.web.controller;

import es.geeko.dto.UsuarioDto;
import es.geeko.service.ProductoService;
import es.geeko.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {

    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    public HomeController(UsuarioService usuarioService, ProductoService productoService) {
        this.usuarioService = usuarioService;
        this.productoService = productoService;
    }

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
    public String getHomePage(ModelMap interfazConPantalla) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);
        return "usuarios/inicio";
    }


    @GetMapping("/accessDenied")
    public String getAccessDeniedPage() {
        return "accessDeniedPage";
    }
}