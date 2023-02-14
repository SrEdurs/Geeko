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

    @GetMapping("/baneado")
    public String getBanPage() {
        return "/usuarios/baneado";
    }

    @GetMapping("/home")
    public String getHomePage(ModelMap interfazConPantalla) {

        //DTO del usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Si está presente, mostramos
        if(usuarioDto.isPresent()) {
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario", attr);

            if (attr.getActivo() == 0) {
                return "redirect:/baneado";
            }

            if (attr.getTematicas().isEmpty()) {

                return "redirect:/cuestionario";

            } else {

                final List<Producto> listaProductos = productoRepository.findTop8ProductosByTematicaIsInAndGeekoIsAndActivoIsOrderByIdDesc(usuarioDto.get().getTematicas(), 1, 1);
                interfazConPantalla.addAttribute("listaProductos", listaProductos);

                final List<Producto> listaProductosParaTi = productoRepository.findTop8ProductosByTematicaIsInAndGeekoIsAndActivoIsOrderByIdDesc(usuarioDto.get().getTematicas(), 0, 1);
                interfazConPantalla.addAttribute("listaProductosParaTi", listaProductosParaTi);

                return "usuarios/inicio";
            }

        } else{
            return "error";
        }
    }


    @GetMapping("/accessdenied")
    public String getAccessDeniedPage() {
        return "accessdenied";
    }
}