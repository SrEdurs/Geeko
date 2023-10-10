package es.geeko.web.controller;

import es.geeko.dto.ComentarioDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.Comentario;
import es.geeko.model.Producto;
import es.geeko.model.Usuario;
import es.geeko.model.Like;
import es.geeko.repository.ComentarioRepository;
import es.geeko.repository.ProductoRepository;
import es.geeko.service.ComentarioService;
import es.geeko.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final UsuarioService usuarioService;
    private final ProductoRepository productoRepository;
    private final ComentarioRepository comentarioRepository;
    private final ComentarioService comentarioService;

    public HomeController(UsuarioService usuarioService, ProductoRepository productoRepository, ComentarioRepository comentarioRepository, ComentarioService comentarioService) {
        this.usuarioService = usuarioService;
        this.productoRepository = productoRepository;
        this.comentarioRepository = comentarioRepository;
        this.comentarioService = comentarioService;
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

    @GetMapping("/crearcuenta")
    public String register() {
        return "usuarios/crearcuenta";
    }


    @GetMapping("/home")
    public String getHomePage(ModelMap interfazConPantalla) throws Exception {

        //DTO del usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Si está presente, mostramos
        if(usuarioDto.isPresent()) {

            //Creamos el DTO del nuevo comentario y lo mandamos a la pantalla
            final ComentarioDto comentarioDto = new ComentarioDto();
            comentarioDto.setProducto(null);
            interfazConPantalla.addAttribute("datosComentario",comentarioDto);

            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario", attr);

            if (attr.getActivo() == 0) {
                return "redirect:/baneado";
            }

            if (attr.getTematicas().isEmpty()) {

                return "redirect:/cuestionario";

            } else {

                List<Long> likes = new ArrayList<Long>();
                List<Long> ids = new ArrayList<Long>();

                Usuario attr2 = this.usuarioService.getMapper().toEntity(usuarioDto.get());
                attr.getSeguimientos().add(attr2);
    
                for (Usuario elemento : attr.getSeguimientos()) {
                    ids.add(elemento.getId());                                               
                  }
    
                  for (Like elemento : attr.getLikes()) {
                    likes.add(elemento.getComentarioLike().getId());
                  }


            for (Like elemento : attr.getLikes()) {
                System.out.println(elemento.getComentarioLike().getId());
                likes.add(elemento.getComentarioLike().getId());
              }
    
                interfazConPantalla.addAttribute("ids", ids);
                interfazConPantalla.addAttribute("likes", likes);

                final List<Comentario> listaComentarios = comentarioRepository.findTop8ComentariosByActivoIsAndUsuarioInOrderByIdDesc(1, attr.getSeguimientos());
                interfazConPantalla.addAttribute("listaComentarios", listaComentarios);

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

    @PostMapping("/home")
    public String guardarComentario(ComentarioDto comentarioDto, ModelMap interfazConPantalla){

        //Datos del usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);

        //Asignación del nuevo comentario
        Comentario comentario = new Comentario();
        comentario.setUsuario(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()));
        comentario.setProducto(null);
        comentario.setTexto(comentarioDto.getTexto());
        comentario.setActivo(1);
        comentarioService.getRepo().save(comentario);

        return "redirect:/home";
    }


    @GetMapping("/accessdenied")
    public String getAccessDeniedPage() {
        return "accessdenied";
    }
}
