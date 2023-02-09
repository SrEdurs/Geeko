package es.geeko.web.controller;

import es.geeko.dto.ComentarioDto;
import es.geeko.dto.LoginDto;
import es.geeko.dto.ProductoDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.Comentario;
import es.geeko.model.Producto;
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

    @GetMapping("/crearcomentario/{id}")
    public String vistaCrearComentario(@PathVariable("id") Integer id,ModelMap interfazConPantalla){

        final ComentarioDto comentarioDto = new ComentarioDto();
        comentarioDto.setProducto(this.productoService.getRepo().getReferenceById(id));

        interfazConPantalla.addAttribute("datosComentario",comentarioDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        Optional<ProductoDto> producto = productoService.encuentraPorId(id);

        if(usuarioDto.isPresent()) {

            ProductoDto productoDto =  producto.get();
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario", attr);
            interfazConPantalla.addAttribute("datosProducto",productoDto);
            return "social/crearcomentario";
        } else{
            return "error";
        }

    }

    @PostMapping("/crearcomentario/{id}")
    public String guardarComentario(@PathVariable("id") Integer id, ComentarioDto comentarioDto, ModelMap interfazConPantalla) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);

        comentarioDto.setUsuario(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()));
        comentarioDto.setProducto(this.productoService.getRepo().findProductoByIdIs(id));
        comentarioDto.setImagen(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()).getAvatar());

        this.comentarioService.guardar(comentarioDto);

            return String.format("redirect:/productos/{id}", id);

    }
}
