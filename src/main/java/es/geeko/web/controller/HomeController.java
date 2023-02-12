package es.geeko.web.controller;

import es.geeko.dto.UsuarioDto;
import es.geeko.model.Comentario;
import es.geeko.model.Producto;
import es.geeko.repository.ComentarioRepository;
import es.geeko.repository.ProductoRepository;
import es.geeko.service.ProductoService;
import es.geeko.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final UsuarioService usuarioService;
    private final ProductoService productoService;
    private final ComentarioRepository comentarioRepository;
    private final ProductoRepository productoRepository;

    public HomeController(UsuarioService usuarioService, ProductoService productoService, ComentarioRepository comentarioRepository, ProductoRepository productoRepository) {
        this.usuarioService = usuarioService;
        this.productoService = productoService;
        this.comentarioRepository = comentarioRepository;
        this.productoRepository = productoRepository;
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

        final List<Comentario> listaComentarios = comentarioRepository.findComentarioByUsuarioAndActivo(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()),1 );
        interfazConPantalla.addAttribute("listaComentarios",listaComentarios);

        final List<Producto> listaProductos = productoRepository.findProductosByUsuarioId(attr.getId());
        interfazConPantalla.addAttribute("listaProductos",listaProductos);
        return "usuarios/inicio";
    }


    @GetMapping("/accessDenied")
    public String getAccessDeniedPage() {
        return "accessDeniedPage";
    }
}