package es.geeko.web.controller;

import es.geeko.dto.ProductoDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.Producto;
import es.geeko.model.Tematica;
import es.geeko.model.Usuario;
import es.geeko.repository.ProductoRepository;
import es.geeko.service.IUserService;
import es.geeko.service.ProductoService;
import es.geeko.service.TematicaService;
import es.geeko.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ProductoService productoService;

    @Autowired
    private UserDetailsService uds;
    private final ProductoRepository productoRepository;

    public AppUsuariosController(UsuarioService usuarioService, TematicaService tematicaService, ProductoService productoService,
                                 ProductoRepository productoRepository) {
        this.usuarioService = usuarioService;
        this.tematicaService = tematicaService;
        this.productoService = productoService;
        this.productoRepository = productoRepository;
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
    public String perfil(Integer id, ModelMap interfazConPantalla){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        final List<Producto> listaProductos = productoRepository.findProductosByTituloIsNotLikeAndUsuarioIsNull("prueba");
        interfazConPantalla.addAttribute("listaProductos",listaProductos);
        if (usuarioDto.isPresent()){
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario",attr);
            return "usuarios/perfil";
        } else{
            return "error";
        }
    }

    /*@GetMapping("usuarios/cuestionario/{idusr}")
    public String vistaCuestionario(@PathVariable("idusr") Integer id, ModelMap interfazConPantalla){
        final List<Tematica> tematicas = tematicaService.buscarEntidades();
        interfazConPantalla.addAttribute("listaTematicas",tematicas);
        return "usuarios/cuestionario";
    }

    @PostMapping("/usuarios/cuestionario/{idusr}")
    public String guardarCuestionario(@ModelAttribute Usuario user, @PathVariable("idusr") Integer id, UsuarioDto usuarioDtoEntrada){

        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<UsuarioDto> usuarioDtoControl = this.usuarioService.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (usuarioDtoControl.isPresent()) {
            System.out.println("EEEEEEEEEEEEEEEEE");
            UsuarioDto usuarioDtoGuardar = new UsuarioDto();
            usuarioDtoGuardar.setId(id);
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
            usuarioDtoGuardar.setTematicas(usuarioDtoEntrada.getTematicas());
        }


        return String.format("redirect:/");
    }*/

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

    /*
    @GetMapping("/usuarios/{idusr}")
    public String vistaDatosUsuario(@PathVariable("idusr") Integer id, ModelMap interfazConPantalla){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(id);
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
    @PostMapping("/usuarios/{idusr}")
    public String guardarEdicionDatosUsuario(@PathVariable("idusr") Integer id, UsuarioDto usuarioDtoEntrada) throws Exception {
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<UsuarioDto> usuarioDtoControl = this.usuarioService.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (usuarioDtoControl.isPresent()){
            UsuarioDto usuarioDtoGuardar =  new UsuarioDto();
            usuarioDtoGuardar.setId(id);
            usuarioDtoGuardar.setEmilio(usuarioDtoEntrada.getEmilio());
            usuarioDtoGuardar.setUsuario(usuarioDtoEntrada.getUsuario());
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

        System.out.println("usuarioDtoEntrada = " + usuarioDtoEntrada.getClave());
            //Obtenemos la password del servicio

            this.usuarioService.guardar(usuarioDtoGuardar);

            System.out.println("usuarioDtoEntrada = " + usuarioDtoGuardar.getClave());
            return String.format("redirect:/usuarios/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "index";
        }

    }

*/


    @GetMapping("/usuarios/perfilotrousuario")
    public String vistaotroperfil(){
        return "/usuarios/perfilotrousuario";
    }

    @GetMapping("/usuarios/perfiladmin")
    public String vistaperfiladmin(){
        return "/usuarios/perfiladmin";
    }

    @GetMapping("/social/social")
    public String vistasocial(){
        return "/social/social";
    }

    @GetMapping("/usuarios/cambiarcontraseña")
    public String vistaOlvidona(){
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

}
