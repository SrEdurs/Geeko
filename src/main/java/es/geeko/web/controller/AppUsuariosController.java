package es.geeko.web.controller;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.Comentario;
import es.geeko.model.Like;
import es.geeko.model.Producto;
import es.geeko.model.Tematica;
import es.geeko.model.Usuario;
import es.geeko.repository.ComentarioRepository;
import es.geeko.repository.ProductoRepository;
import es.geeko.repository.LikeRepository;
import es.geeko.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class AppUsuariosController extends AbstractController<UsuarioDto> {


    @Autowired
    private IUserService userService;
    private final UsuarioService usuarioService;
    private final TematicaService tematicaService;
    private final ComentarioService comentarioService;
    private final LikeRepository likeRepository;

    @Autowired
    private final ProductoRepository productoRepository;
    private final ComentarioRepository comentarioRepository;

    public AppUsuariosController(UsuarioService usuarioService, TematicaService tematicaService,
                                 ProductoRepository productoRepository,
                                 ComentarioRepository comentarioRepository,
                                 ComentarioService comentarioService,
                                 LikeRepository likeRepository) {
        this.usuarioService = usuarioService;
        this.tematicaService = tematicaService;
        this.productoRepository = productoRepository;
        this.comentarioRepository = comentarioRepository;
        this.comentarioService = comentarioService;
        this.likeRepository = likeRepository;
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute Usuario user, Model model){

        //check if user exists, if not, save it
        if(userService.checkUserExists(user.getEmilio())){
            model.addAttribute("msg", "El usuario ya existe!");
            return "usuarios/crearcuenta";
        } else{
            Integer id = userService.saveUser(user);
            String message = "User '"+id+"' saved successfully !";
            model.addAttribute("msg", message);
            return String.format("redirect:/bienvenida");
        }      

    }

    @GetMapping("/cambiarclave")

    public String vistaPass(ModelMap interfazConPantalla){

        //Datos del usuario de la sesión
        usuarioSesion(interfazConPantalla);

        //Lista de temáticas entre las que elegir
        final List<Tematica> tematicas = tematicaService.buscarEntidades();
        interfazConPantalla.addAttribute("listaTematicas",tematicas);

        return "usuarios/cambiarcontraseña";
    }

    //Cambiar la contraseña, una vez cambiada, devuelve a la pantalla de editar perfil
    @PostMapping("/cambiarclave")
    public String cambiarclave(@ModelAttribute Usuario user, Model model){

        //Necesitamos el DTO del usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        System.out.println("000000000000000000000000000000000000000000000000000000000000000");
        //Si está presente, mostramos los datos
        if(usuarioDto.isPresent()) {

            System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
            //Comprobamos que la contraseña actual es correcta
            if(userService.checkUserExists(user.getEmilio())){

                System.out.println("11111111111111111111111111111111111111111111111111111111111111111111");

                //Comprobamos que la nueva contraseña es igual a la repetida
                if(user.getClave().equals(user.getClave())){

                    System.out.println("2222222222222222222222222222222222222222222222222222222222222222222222222");

                    //Cambiamos la contraseña
                    userService.changePassword(user.getEmilio(), user.getClave());

                    //Mostramos mensaje de éxito
                    model.addAttribute("msg", "Contraseña cambiada correctamente");
                    return String.format("redirect:/cambiarclave");

                }else{
                    //Mostramos mensaje de error
                    model.addAttribute("msg", "Las contraseñas no coinciden");
                    return String.format("redirect:/cambiarclave");
                }

            }else{
                //Mostramos mensaje de error
                System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                model.addAttribute("msg", "La contraseña actual no es correcta");
                return String.format("redirect:/cambiarclave");
            }

        }else{
            //Mostramos mensaje de error
            model.addAttribute("msg", "No se ha podido cambiar la contraseña");
            return String.format("redirect:/cambiarclave");
        }

    }



    //Getmapping bienvenida
    @GetMapping("/bienvenida")
    public String bienvenida(){
        return "usuarios/bienvenida";
    }


    @GetMapping("/perfil")
    public String perfil(ModelMap interfazConPantalla){

        //Necesitamos el DTO del usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Si está presente, mostramos los datos
        if(usuarioDto.isPresent()) {

            //Lista de los productos dentro de sus gustos
            final List<Producto> listaProductos = productoRepository.findTop5ProductosByTematicaIsInAndGeekoIsAndActivoIsOrderByIdDesc(usuarioDto.get().getTematicas(), 1, 1);
            interfazConPantalla.addAttribute("listaIntereses", listaProductos);

            //Lista de sus comentarios
            final List<Comentario> listaComentarios = comentarioRepository.findComentarioByUsuarioAndActivoOrderByIdDesc(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()), 1);
            interfazConPantalla.addAttribute("listaComentarios", listaComentarios);

            //Datos del usuario
            UsuarioDto attr = usuarioDto.get();
            UsuarioDto perf = usuarioDto.get();
            //List<Like> likes = likeRepository.findByUsuarioLikeIs(this.usuarioService.getMapper().toEntity(attr));


            List<Long> likes = new ArrayList<Long>();
            List<Long> ids = new ArrayList<Long>();

            for (Usuario elemento : attr.getSeguimientos()) {
                ids.add(elemento.getId());
              }

              for (Like elemento : attr.getLikes()) {
                System.out.println(elemento.getComentarioLike().getId());
                likes.add(elemento.getComentarioLike().getId());
              }

            interfazConPantalla.addAttribute("ids", ids);
            interfazConPantalla.addAttribute("likes", likes);
            interfazConPantalla.addAttribute("datosPerfil", perf);
            interfazConPantalla.addAttribute("datosUsuario", attr);

            return "usuarios/perfil";

        } else{
            return "error";
        }

    }

    @GetMapping("/perfil/{id}")
    public String perfil(@PathVariable("id") Long id, ModelMap interfazConPantalla){

        //Datos del usuario de la sesión y sus intereses
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //DTO del usuario a mostrar
        Optional<UsuarioDto> usuarioPerfil = this.usuarioService.encuentraPorId(id);

        if (usuarioPerfil.isPresent()){
            UsuarioDto perf = usuarioPerfil.get();
            UsuarioDto attr = usuarioDto.get();

            //Lista de comentarios del usuario
            final List<Comentario> listaComentarios = comentarioRepository.findComentarioByUsuarioAndActivoOrderByIdDesc(this.usuarioService.getRepo().getUsuarioByIdIs(id),1 );
            interfazConPantalla.addAttribute("listaComentarios",listaComentarios);
            interfazConPantalla.addAttribute("datosPerfil",perf);
            interfazConPantalla.addAttribute("datosUsuario", attr);

            final List<Producto> listaProductos = productoRepository.findTop5ProductosByTematicaIsInAndGeekoIsAndActivoIsOrderByIdDesc(usuarioDto.get().getTematicas(), 1, 1);
            interfazConPantalla.addAttribute("listaIntereses", listaProductos);

            List<Long> ids = new ArrayList<Long>();
            List<Long> likes = new ArrayList<Long>();

            for (Usuario elemento : attr.getSeguimientos()) {
                System.out.println(elemento.getId());
                ids.add(elemento.getId());
              }

              for (Like elemento : attr.getLikes()) {
                System.out.println(elemento.getComentarioLike().getId());
                likes.add(elemento.getComentarioLike().getId());
              }

              interfazConPantalla.addAttribute("likes", likes);
              interfazConPantalla.addAttribute("ids", ids);

            return "usuarios/perfil";

        } else{
            return "error";
        }
    }

    @GetMapping("/cuestionario")
    public String vistaCuestionario(ModelMap interfazConPantalla){

        //Datos del usuario de la sesión
        usuarioSesion(interfazConPantalla);

        //Lista de temáticas entre las que elegir
        final List<Tematica> tematicas = tematicaService.buscarEntidades();
        interfazConPantalla.addAttribute("listaTematicas",tematicas);

        return "usuarios/cuestionario";
    }

    @PostMapping("/cuestionario")
    public String guardarCuestionario(UsuarioDto usuarioDtoEntrada ) throws Exception {

        //Necesitamos obtener el usuario actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Si está presente, le asignamos el list de temáticas seleccionadas
        if(usuarioDto.isPresent()){

        UsuarioDto attr = usuarioDto.get();
        attr.setTematicas(usuarioDtoEntrada.getTematicas());

        //Guardamos
        this.usuarioService.guardar(attr);

        return "redirect:/home";

        } else{
            return "error";
        }
    }

    @GetMapping("usuarios/edit")
    public String vistaDatosUsuario(ModelMap interfazConPantalla){

        usuarioSesionConIntereses(interfazConPantalla);

        return "usuarios/edit";

    }

    @PostMapping("usuarios/edit")
    public String guardarEdicionDatosUsuario(UsuarioDto usuarioDtoEntrada) throws Exception {

        //Necesitamos el usuario actual
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDtoControl = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Creamos un DTO que recibe los datos especificados
        UsuarioDto usuarioDtoGuardar =  new UsuarioDto();

        //Le asignamos los datos introducidos
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

        //Le asignamos los datos del usuario actual
        usuarioDtoGuardar.setId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        usuarioDtoGuardar.setClave(usuarioDtoControl.get().getClave());
        usuarioDtoGuardar.setRoles(usuarioDtoControl.get().getRoles());
        usuarioDtoGuardar.setTematicas(usuarioDtoControl.get().getTematicas());
        usuarioDtoGuardar.setTransacciones(usuarioDtoControl.get().getTransacciones());
        usuarioDtoGuardar.setSeguimientos(usuarioDtoControl.get().getSeguimientos());
        usuarioDtoGuardar.setSeguidos(usuarioDtoControl.get().getSeguidos());
        usuarioDtoGuardar.setLikes(usuarioDtoControl.get().getLikes());
        usuarioDtoGuardar.setComentarios(usuarioDtoControl.get().getComentarios());
        usuarioDtoGuardar.setProductos(usuarioDtoControl.get().getProductos());
        usuarioDtoGuardar.setChats(usuarioDtoControl.get().getChats());
        usuarioDtoGuardar.setMensajes(usuarioDtoControl.get().getMensajes());
        usuarioDtoGuardar.setReportado(usuarioDtoControl.get().getReportado());
        usuarioDtoGuardar.setReportes(usuarioDtoControl.get().getReportes());
        


        //Guardamos
        this.usuarioService.guardar(usuarioDtoGuardar);

        return "redirect:/usuarios/edit";
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

    public void usuarioSesionConIntereses(ModelMap interfazConPantalla){

        //Obtenemos el DTO del usuario actual por ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Si está presente, mostramos sus datos e intereses
        if(usuarioDto.isPresent()) {
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario", attr);

            final List<Producto> listaIntereses = productoRepository.findTop5ProductosByTematicaIsInAndGeekoIsAndActivoIsOrderByIdDesc(usuarioDto.get().getTematicas(), 1, 1);
            interfazConPantalla.addAttribute("listaIntereses", listaIntereses);
        }
    }


    @GetMapping("/cambiamegusta/{id}")
    public ResponseEntity<String> cambiaMeGusta(@PathVariable("id") Long id) {
        Optional<Comentario> comentarioOptional = comentarioRepository.findComentarioByIdIs(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDtoOptional = usuarioService.encuentraPorId(usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        if (comentarioOptional.isPresent() && usuarioDtoOptional.isPresent()) {

            Comentario comentario = comentarioOptional.get();
            Integer likes = comentario.getLikes();
            likes++;
            comentario.setLikes(likes);

            UsuarioDto usuarioDto = usuarioDtoOptional.get();
            Like like = new Like();
            like.setUsuarioLike(usuarioService.getMapper().toEntity(usuarioDto));
            like.setComentarioLike(comentario);
            likeRepository.save(like);

            List<Like> listaLikes = new ArrayList<>();
            listaLikes.add(like);
            comentario.setLikesComen(listaLikes);
            comentarioRepository.save(comentario);

            return new ResponseEntity<>(likes.toString() ,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}


    @GetMapping("/nomegusta/{id}")
    public ResponseEntity<String> noMeGusta(@PathVariable("id") Long id){
        // Buscamos el comentario a procesar
        Integer likes = 0;
        Optional<Comentario> coment = comentarioRepository.findComentarioByIdIs(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        if(coment.isPresent() && usuarioDto.isPresent()){

            //likes = coment.get().getLikes();
            //coment.get().setLikes(--likes);         

            UsuarioDto attr = usuarioDto.get();
            List<Like> likeses = coment.get().getLikesComen();

            for (Like elemento : likeses) {
                System.out.println(elemento.getUsuarioLike().getId());

                if(elemento.getUsuarioLike().getId() == attr.getId()){
                    
                    System.out.println("Coincide!");
                    likes = coment.get().getLikes();
                    coment.get().setLikes(--likes);
                    likeRepository.delete(elemento);

                }
              }

     
            //likeRepository.delete(like);
            
            //comentarioRepository.save(coment.get());
        }
        return new ResponseEntity<>(likes.toString(),HttpStatus.OK);
    }

   
}
