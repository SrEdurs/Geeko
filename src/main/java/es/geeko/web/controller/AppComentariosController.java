package es.geeko.web.controller;

import es.geeko.dto.ComentarioDto;
import es.geeko.dto.ProductoDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.Comentario;
import es.geeko.model.Producto;
import es.geeko.repository.ComentarioRepository;
import es.geeko.repository.ProductoRepository;
import es.geeko.service.ComentarioService;
import es.geeko.service.ProductoService;
import es.geeko.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AppComentariosController extends AbstractController<ComentarioDto> {

    private final UsuarioService usuarioService;
    private final ProductoService productoService;
    private final ComentarioRepository comentarioRepository;
    private final ProductoRepository productoRepository;
    private final ComentarioService comentarioService;

    public AppComentariosController(UsuarioService usuarioService, ProductoService productoService,
                                    ComentarioRepository comentarioRepository,ProductoRepository productoRepository, ComentarioService comentarioService) {
        this.usuarioService = usuarioService;
        this.productoService = productoService;
        this.comentarioRepository = comentarioRepository;
        this.productoRepository = productoRepository;
        this.comentarioService = comentarioService;
    }

    @GetMapping("/crearcomentario/{id}")
    public String vistaCrearComentario(@PathVariable("id") Integer id,ModelMap interfazConPantalla){

        //Creamos el DTO y lo mandamos a la pantalla
        final ComentarioDto comentarioDto = new ComentarioDto();
        comentarioDto.setProducto(this.productoService.getRepo().getReferenceById(id));
        interfazConPantalla.addAttribute("datosComentario",comentarioDto);


        //Usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Podría interesarte sección derecha
        final List<Producto> listaProductos = productoRepository.findTop5ProductosByTematicaIsInAndGeekoIsAndActivoIs(usuarioDto.get().getTematicas(), 1,1);

        //Producto al que va unido el comentario
        Optional<ProductoDto> producto = productoService.encuentraPorId(id);

        //Si el usuario de la sesión está presente, mostramos por pantalla (Thymeleaf no entiende el Optional)
        if(usuarioDto.isPresent()) {

            ProductoDto productoDto =  producto.get();
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("listaProductos",listaProductos);
            interfazConPantalla.addAttribute("datosUsuario", attr);
            interfazConPantalla.addAttribute("datosProducto",productoDto);
            return "social/crearcomentario";
        } else{
            return "error";
        }

    }

    @PostMapping("/crearcomentario/{id}")
    public String guardarComentario(@PathVariable("id") Integer id, ComentarioDto comentarioDto, ModelMap interfazConPantalla) throws Exception {

        //Datos de usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);

        //Asignación de los datos del nuevo comentario
        Comentario comentario = new Comentario();
        comentario.setUsuario(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()));
        comentario.setProducto(this.productoService.getRepo().findProductoByIdIs(id));
        comentario.setImagen(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()).getAvatar());
        comentario.setTitulo(comentarioDto.getTitulo());
        comentario.setTexto(comentarioDto.getTexto());
        comentario.setActivo(1);
        comentarioRepository.save(comentario);

        //Redireccionamos al producto al que pertenece el comentario
        return String.format("redirect:/productos/{id}", id);

    }

    //Método para borrar un comentario con Javascript
    @GetMapping("/borrar/{id}")
    public ResponseEntity<String> borrar(@PathVariable("id") Integer id){
        // Buscamos el comentario a procesar
        Integer activo = 0;
        Optional<Comentario> coment = comentarioService.encuentraPorIdEntity(id);
        if(coment.isPresent()){
            coment.get().setActivo(0);
            comentarioRepository.save(coment.get());
        }
        return new ResponseEntity<>(activo.toString(), HttpStatus.OK);
    }
}
