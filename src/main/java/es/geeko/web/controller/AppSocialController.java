package es.geeko.web.controller;

import es.geeko.dto.UsuarioDto;
import es.geeko.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import es.geeko.model.Usuario;
import es.geeko.repository.UsuarioRepository;

@Controller
public class AppSocialController extends AbstractController<UsuarioDto> {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;



    public AppSocialController(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/social")
    public String social(ModelMap interfazConPantalla){

        usuarioSesion(interfazConPantalla);
        return "/social/social";
    }

    @GetMapping("/seguir/{id}")
    public ResponseEntity<String> seguir(@PathVariable("id") Long id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        
        Optional<Usuario> usuario = usuarioService.encuentraPorIdEntity(id);

        if(usuario.isPresent()){

            Usuario attr = this.usuarioService.getMapper().toEntity(usuarioDto.get());
            usuario.get().getSeguidos().add(attr);
            usuarioRepository.save(usuario.get());

            attr.getSeguimientos().add(usuario.get());
            //usuarioRepository.save(attr);
            System.out.println("EOOOOOOOOOOOOOOOOOOOOOOOO");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/noseguir/{id}")
    public ResponseEntity<String> noseguir(@PathVariable("id") Long id){
        System.out.println("EYYYYYYYYYYYYYYYYYYYYYYY");
        //Obtenemos el DTO del usuario actual por ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        
        Optional<Usuario> usuario = usuarioService.encuentraPorIdEntity(id);

        if(usuario.isPresent()){

            Usuario attr = this.usuarioService.getMapper().toEntity(usuarioDto.get());

            usuario.get().getSeguidos().remove(attr);

            usuarioRepository.save(usuario.get());

            System.out.println("EYOO");

            attr.getSeguimientos().remove(usuario.get());

            usuarioRepository.save(usuario.get());
            usuarioRepository.save(attr);
            System.out.println("EYYYYYYYYYYYYYYYYYYYYYYY");
        }
        return new ResponseEntity<>(HttpStatus.OK);
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