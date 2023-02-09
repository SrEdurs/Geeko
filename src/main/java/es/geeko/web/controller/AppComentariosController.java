package es.geeko.web.controller;

import es.geeko.dto.ComentarioDto;
import es.geeko.dto.LoginDto;
import es.geeko.dto.ProductoDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.service.ComentarioService;
import es.geeko.service.ProductoService;
import es.geeko.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AppComentariosController extends AbstractController<ComentarioDto> {

    private final ComentarioService comentarioService;
    private final UsuarioService usuarioService;
    private final ProductoService productoService;

    public AppComentariosController(ComentarioService comentarioService, UsuarioService usuarioService, ProductoService productoService) {
        this.comentarioService = comentarioService;
        this.usuarioService = usuarioService;
        this.productoService = productoService;
    }

    @GetMapping("/crearcomentario")
    public String vistaCrearComentario(ModelMap interfazConPantalla){
        //Instancia en memoria del dto a informar en la pantalla
        final ComentarioDto comentarioDto = new ComentarioDto();
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datosComentario",comentarioDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);
        return "social/crearcomentario";
    }

    @PostMapping("/crearcomentario")
    public String guardarComentario(ComentarioDto comentarioDto,UsuarioDto usuarioDtoEntrada, ModelMap interfazConPantalla) throws Exception {
        //LLamo al método del servicio para guardar los datos
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        comentarioDto.setImagen(usuarioDto.get().getAvatar());
        comentarioDto.setUsuario(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()));
        ComentarioDto comentarioGuardado =  this.comentarioService.guardar(comentarioDto);
        Long id = comentarioGuardado.getId();




        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);

        return "/perfil";
    }
}
