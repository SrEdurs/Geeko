package es.geeko.web.controller;

import es.geeko.dto.ProductoDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.Comentario;
import es.geeko.model.Producto;
import es.geeko.model.Tematica;
import es.geeko.model.Usuario;
import es.geeko.repository.ComentarioRepository;
import es.geeko.repository.ProductoRepository;
import es.geeko.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class AppUsuariosController extends AbstractController<UsuarioDto> {


    @Autowired
    private IUserService userService;
    private final UsuarioService usuarioService;
    private final TematicaService tematicaService;
    private final ComentarioService comentarioService;

    @Autowired
    private UserDetailsService uds;
    private final ProductoRepository productoRepository;
    private final ComentarioRepository comentarioRepository;

    public AppUsuariosController(UsuarioService usuarioService, TematicaService tematicaService,
                                 ProductoRepository productoRepository,
                                 ComentarioRepository comentarioRepository,
                                 ComentarioService comentarioService) {
        this.usuarioService = usuarioService;
        this.tematicaService = tematicaService;
        this.productoRepository = productoRepository;
        this.comentarioRepository = comentarioRepository;
        this.comentarioService = comentarioService;
    }

    @GetMapping("/crearcuenta")
    public String register() {
        return "usuarios/crearcuenta";
    }

    @GetMapping("/login")
    public String login(){
        return "usuarios/login";
    }

    // Read Form data to save into DB
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute Usuario user, Model model){
        System.out.println("EYYYYYYYY");
        Integer id = userService.saveUser(user);
        String message = "User '"+id+"' saved successfully !";
        model.addAttribute("msg", message);
        //return String.format("redirect:/usuarios/%s", id);
        return String.format("redirect:/");

    }

    @GetMapping("/perfil")
    public String perfil(ModelMap interfazConPantalla){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        final List<Producto> listaProductos = productoRepository.findProductosByTituloIsNotLikeAndUsuarioIsNull("prueba");
        interfazConPantalla.addAttribute("listaProductos",listaProductos);

        final List<Comentario> listaComentarios = comentarioRepository.findComentarioByUsuarioAndActivo(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()),1 );
        interfazConPantalla.addAttribute("listaComentarios",listaComentarios);

        if (usuarioDto.isPresent()){
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario",attr);
            return "usuarios/perfil";
        } else{
            return "error";
        }
    }

    @GetMapping("/cuestionario")
    public String vistaCuestionario(ModelMap interfazConPantalla){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);

        final List<Tematica> tematicas = tematicaService.buscarEntidades();
        interfazConPantalla.addAttribute("listaTematicas",tematicas);

        return "usuarios/cuestionario";
    }

    @PostMapping("/cuestionario")
    public String guardarCuestionario(UsuarioDto usuarioDtoEntrada){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        //¿Debería comprobar si hay datos?
        if (usuarioDto.isPresent()) {
            System.out.println("EEEEEEEEEEEEEEEEE");
            UsuarioDto usuarioDtoGuardar = new UsuarioDto();;
            usuarioDtoGuardar.setEmilio(usuarioDtoEntrada.getEmilio());
            usuarioDtoGuardar.setNick(usuarioDtoEntrada.getNick());
            usuarioDtoGuardar.setNombre(usuarioDtoEntrada.getNombre());
            usuarioDtoGuardar.setApellidos(usuarioDtoEntrada.getApellidos());
            usuarioDtoGuardar.setAvatar(usuarioDtoEntrada.getAvatar());
            usuarioDtoGuardar.setDireccion1(usuarioDtoEntrada.getDireccion1());
            usuarioDtoGuardar.setDireccion2(usuarioDtoEntrada.getDireccion2());
            usuarioDtoGuardar.setPoblacion(usuarioDtoEntrada.getPoblacion());
            usuarioDtoGuardar.setProvincia(usuarioDtoEntrada.getProvincia());
            usuarioDtoGuardar.setTlf(usuarioDtoEntrada.getTlf());
            usuarioDtoGuardar.setBiografia(usuarioDtoEntrada.getBiografia());
            usuarioDtoGuardar.setClave(usuarioDto.get().getClave());
            usuarioDtoGuardar.setTematicas(usuarioDtoEntrada.getTematicas());
        }
        return String.format("redirect:/perfil");
    }

    @GetMapping("usuarios/edit")
    public String vistaDatosUsuario(ModelMap interfazConPantalla){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        //¿Debería comprobar si hay datos?
        if (usuarioDto.isPresent()){
            //Como encontré datos, obtengo el objeto de tipo "UsuarioDto"
            //addAttribute y thymeleaf no entienden Optional
            UsuarioDto attr = usuarioDto.get();
            System.out.println("La password es:");
            System.out.println(attr.getClave());
            //Asigno atributos y muestro
            interfazConPantalla.addAttribute("datosUsuario",attr);

            return "usuarios/edit";
        } else{
            //Mostrar página usuario no existe
            return "error";
        }
    }

    //Me falta un postmaping para guardar
    @PostMapping("usuarios/edit")
    public String guardarEdicionDatosUsuario(UsuarioDto usuarioDtoEntrada, ModelMap interfazConPantalla) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDtoControl = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        UsuarioDto usuarioDtoGuardar =  new UsuarioDto();
        usuarioDtoGuardar.setId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        usuarioDtoGuardar.setEmilio(usuarioDtoEntrada.getEmilio());
        usuarioDtoGuardar.setNick(usuarioDtoEntrada.getNick());
        usuarioDtoGuardar.setNombre(usuarioDtoEntrada.getNombre());
        usuarioDtoGuardar.setApellidos(usuarioDtoEntrada.getApellidos());
        usuarioDtoGuardar.setAvatar(usuarioDtoEntrada.getAvatar());
        usuarioDtoGuardar.setDireccion1(usuarioDtoEntrada.getDireccion1());
        usuarioDtoGuardar.setDireccion2(usuarioDtoEntrada.getDireccion2());
        usuarioDtoGuardar.setPoblacion(usuarioDtoEntrada.getPoblacion());
        usuarioDtoGuardar.setProvincia(usuarioDtoEntrada.getProvincia());
        usuarioDtoGuardar.setTlf(usuarioDtoEntrada.getTlf());
        usuarioDtoGuardar.setBiografia(usuarioDtoEntrada.getBiografia());
        usuarioDtoGuardar.setClave(usuarioDtoControl.get().getClave());
        this.usuarioService.guardar(usuarioDtoGuardar);
        interfazConPantalla.addAttribute("datosUsuario",usuarioDtoGuardar);
        return String.format("redirect:/perfil");
    }

    @GetMapping("/usuarios/perfilotrousuario")
    public String vistaotroperfil(ModelMap interfazConPantalla){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);
        return "/usuarios/perfilotrousuario";
    }

    @GetMapping("/usuarios/cambiarcontraseña")
    public String vistaOlvidona(ModelMap interfazConPantalla){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);
        return "usuarios/cambiarcontraseña";
    }

    @GetMapping("/social")
    public String vistaOlvidon2a(ModelMap interfazConPantalla){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);
        return "social/social";
    }

    /*@GetMapping("/usuarios/cuestionario")
    public String vistaCuestionario(ModelMap interfazConPantalla){

        final List<Tematica> tematicas = tematicaService.buscarEntidades();
        interfazConPantalla.addAttribute("listaTematicas",tematicas);


        return "usuarios/cuestionario";
    }*/


    @GetMapping("/cambiamegusta/{id}")
    public ResponseEntity<String> cambiaMeGusta(@PathVariable("id") Integer id){
        // Buscamos el comentario a procesar
        Optional<Comentario> coment = comentarioService.encuentraPorIdEntity(id);
        if(coment.isPresent()){
            int likes = coment.get().getLikes();
            coment.get().setLikes(++likes);
            comentarioRepository.save(coment.get());
        }
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

}
