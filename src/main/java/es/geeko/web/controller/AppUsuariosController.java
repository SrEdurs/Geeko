package es.geeko.web.controller;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    @GetMapping("/bienvenida")
    public String bienvenida(){
        return "usuarios/bienvenida";
    }

    // Read Form data to save into DB
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute Usuario user, Model model){
        System.out.println("EYYYYYYYY");
        Integer id = userService.saveUser(user);
        String message = "User '"+id+"' saved successfully !";
        model.addAttribute("msg", message);
        //return String.format("redirect:/usuarios/%s", id);
        return String.format("redirect:/bienvenida");

    }

    @GetMapping("/perfil")
    public String perfil(ModelMap interfazConPantalla){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        final List<Producto> listaProductos = productoRepository.findTop5ProductosByTematicaIsInAndGeekoIsAndActivoIs(usuarioDto.get().getTematicas(), 1,1);
        interfazConPantalla.addAttribute("listaProductos",listaProductos);

        final List<Comentario> listaComentarios = comentarioRepository.findComentarioByUsuarioAndActivo(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()),1 );
        interfazConPantalla.addAttribute("listaComentarios",listaComentarios);

        if (usuarioDto.isPresent()){
            UsuarioDto attr = usuarioDto.get();
            UsuarioDto perf = usuarioDto.get();
            interfazConPantalla.addAttribute("datosPerfil",perf);
            interfazConPantalla.addAttribute("datosUsuario",attr);
            return "usuarios/perfil";
        } else{
            return "error";
        }
    }

    @GetMapping("/perfil/{id}")
    public String perfil(@PathVariable("id") Integer id, ModelMap interfazConPantalla){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        final List<Producto> listaProductos = productoRepository.findTop5ProductosByTematicaIsInAndGeekoIsAndActivoIs(usuarioDto.get().getTematicas(), 1,1);
        interfazConPantalla.addAttribute("listaProductos",listaProductos);

        final List<Comentario> listaComentarios = comentarioRepository.findComentarioByUsuarioAndActivo(this.usuarioService.getRepo().getUsuarioByIdIs(id),1 );
        interfazConPantalla.addAttribute("listaComentarios",listaComentarios);

        Optional<UsuarioDto> usuarioPerfil = this.usuarioService.encuentraPorId(id);

        if (usuarioDto.isPresent()){
            UsuarioDto attr = usuarioDto.get();
            UsuarioDto perf = usuarioPerfil.get();
            interfazConPantalla.addAttribute("datosUsuario",attr);
            interfazConPantalla.addAttribute("datosPerfil",perf);
            return "usuarios/perfil";
        } else{
            return "error";
        }
    }

    @GetMapping("/cuestionario")
    public String vistaCuestionario(ModelMap interfazConPantalla){

        usuarioSesion(interfazConPantalla);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        final UsuarioDto usuario = usuarioDto.get();

        final List<Tematica> tematicas = tematicaService.buscarEntidades();
        interfazConPantalla.addAttribute("listaTematicas",tematicas);

        return "usuarios/cuestionario";
    }

    @PostMapping("/cuestionario")
    public String guardarCuestionario(UsuarioDto usuarioDtoEntrada ) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        UsuarioDto attr = usuarioDto.get();
        attr.setTematicas(usuarioDtoEntrada.getTematicas());

        this.usuarioService.guardar(attr);


        return String.format("redirect:/perfil");
    }

    @GetMapping("usuarios/edit")
    public String vistaDatosUsuario(ModelMap interfazConPantalla){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        if (usuarioDto.isPresent()){

            UsuarioDto attr = usuarioDto.get();
            System.out.println("La password es:");
            System.out.println(attr.getClave());

            interfazConPantalla.addAttribute("datosUsuario",attr);

            return "usuarios/edit";
        } else{

            return "error";
        }
    }

    @PostMapping("usuarios/edit")
    public String guardarEdicionDatosUsuario(UsuarioDto usuarioDtoEntrada) throws Exception {
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

        return String.format("redirect:/perfil");
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

    @GetMapping("/cambiamegusta/{id}")
    public ResponseEntity<String> cambiaMeGusta(@PathVariable("id") Integer id){
        // Buscamos el comentario a procesar
        Integer likes = 0;
        Optional<Comentario> coment = comentarioService.encuentraPorIdEntity(id);
        if(coment.isPresent()){
            likes = coment.get().getLikes();
            coment.get().setLikes(++likes);
            comentarioRepository.save(coment.get());
        }
        return new ResponseEntity<>(likes.toString(),HttpStatus.OK);
    }



    public void usuarioSesion(ModelMap interfazConPantalla){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);
    }

}
