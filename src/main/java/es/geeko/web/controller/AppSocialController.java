package es.geeko.web.controller;

import es.geeko.dto.UsuarioDto;
import es.geeko.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class AppSocialController extends AbstractController<UsuarioDto> {


    @Autowired
    private final UsuarioService usuarioService;

    public AppSocialController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/social")
    public String social(ModelMap interfazConPantalla){

        usuarioSesion(interfazConPantalla);
        return "/social/social";
    }

    public void usuarioSesion(ModelMap interfazConPantalla){

        //Obtenemos el DTO del usuario actual por ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Si está presente, mostramos sus datos
        if(usuarioDto.isPresent()) {
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario", attr);
        }
    }
}
