package es.geeko.web.controller;
import es.geeko.dto.ComentarioDto;
import es.geeko.dto.ProductoDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.Comentario;
import es.geeko.model.Like;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
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
    public String vistaCrearComentario(@PathVariable("id") Long id,ModelMap interfazConPantalla){

        //Creamos el DTO del nuevo comentario y lo mandamos a la pantalla
        final ComentarioDto comentarioDto = new ComentarioDto();
        comentarioDto.setProducto(this.productoService.getRepo().getReferenceById(id));
        interfazConPantalla.addAttribute("datosComentario",comentarioDto);

        //Usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Podría interesarte sección derecha
        final List<Producto> listaProductos = productoRepository.findTop5ProductosByTematicaIsInAndGeekoIsAndActivoIsOrderByIdDesc(usuarioDto.get().getTematicas(), 1,1);

        //Producto al que va unido el comentario
        Optional<ProductoDto> producto = productoService.encuentraPorId(id);

        //Si el producto está presente, mostramos por pantalla (Thymeleaf no entiende el Optional)
        if(producto.isPresent()) {

            ProductoDto productoDto =  producto.get();
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("listaIntereses",listaProductos);
            interfazConPantalla.addAttribute("datosUsuario", attr);
            interfazConPantalla.addAttribute("datosProducto",productoDto);

            return "social/crearcomentario";

        } else{
            return "error";
        }

    }

    @PostMapping("/crearcomentario/{id}")
    public String guardarComentario(@PathVariable("id") Long id, ComentarioDto comentarioDto, ModelMap interfazConPantalla) {

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
        comentario.setTexto(comentarioDto.getTexto());
        comentario.setActivo(1);
        comentarioRepository.save(comentario);

        //Redireccionamos al producto al que pertenece el comentario
        return "redirect:/productos/" + id;

    }

    @GetMapping("/crearrespuesta/{idpro}/{idcom}")
    public String vistaCrearRespuesta(@PathVariable("idpro") Long idpro, @PathVariable("idcom") Long idcom,ModelMap interfazConPantalla){

        //Creamos el DTO del nuevo comentario y lo mandamos a la pantalla
        final ComentarioDto comentarioDto = new ComentarioDto();
        comentarioDto.setProducto(this.productoService.getRepo().getReferenceById(idpro));
        comentarioDto.setComentarioPadre(this.comentarioService.getMapper().toDto(this.comentarioService.getRepo().findComentariosByIdIs(idcom)));
        interfazConPantalla.addAttribute("datosComentario",comentarioDto);

        //Usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Podría interesarte sección derecha
        final List<Producto> listaProductos = productoRepository.findTop5ProductosByTematicaIsInAndGeekoIsAndActivoIsOrderByIdDesc(usuarioDto.get().getTematicas(), 1,1);

        //Producto al que va unido el comentario
        Optional<ProductoDto> producto = productoService.encuentraPorId(idpro);

        //Si el producto está presente, mostramos por pantalla (Thymeleaf no entiende el Optional)
        if(producto.isPresent()) {

            ProductoDto productoDto =  producto.get();
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("listaIntereses",listaProductos);
            interfazConPantalla.addAttribute("datosUsuario", attr);
            interfazConPantalla.addAttribute("datosProducto",productoDto);

            return "social/crearcomentario";

        } else{
            return "error";
        }

    }

    @PostMapping("/crearrespuesta/{idpro}/{idcom}")
    public String guardarRespuesta(@PathVariable("idpro") Long idpro, @PathVariable("idcom") Long idcom, ComentarioDto comentarioDto, ModelMap interfazConPantalla) {

        //Datos de usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);

        //Asignación de los datos del nuevo comentario
        Comentario comentario = new Comentario();
        comentario.setUsuario(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()));
        comentario.setProducto(this.productoService.getRepo().findProductoByIdIs(idpro));
        comentario.setTexto(comentarioDto.getTexto());
        comentario.setActivo(1);
        comentario.setComentarioPadre(this.comentarioService.getRepo().findComentariosByIdIs(idcom));
        comentarioRepository.save(comentario);

        Comentario comentarioPadre = this.comentarioService.getRepo().findComentariosByIdIs(idcom);
        comentarioPadre.getComentariosHijos().add(comentario);
        this.comentarioService.getRepo().save(comentarioPadre);

        //Redireccionamos al producto al que pertenece el comentario
        return "redirect:/respuestas/" + idcom;

    }

    @GetMapping("/respuestas/{id}")
    public String respuestasComentario(@PathVariable("id") Long id, ModelMap interfazConPantalla) {

        //Usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        Optional<Comentario> comentarioOptional = comentarioRepository.findComentarioByIdIs(id);
        Optional<ProductoDto> productoDto = productoService.encuentraPorId(comentarioOptional.get().getProducto().getId());

        if (comentarioOptional.isPresent() && usuarioDto.isPresent()) {
            UsuarioDto attr = usuarioDto.get();
            Comentario comentario = comentarioOptional.get();
            List<Comentario> respuestas = comentarioRepository.findComentariosByComentarioPadreAndActivo(comentario, 1);
            Collections.reverse(respuestas);
            List<Long> likes = new ArrayList<Long>();

            for (Like elemento : attr.getLikes()) {
                System.out.println(elemento.getComentarioLike().getId());
                likes.add(elemento.getComentarioLike().getId());
              }

            //Podría interesarte sección derecha
            final List<Producto> listaProductos = productoRepository.findTop5ProductosByTematicaIsInAndGeekoIsAndActivoIsOrderByIdDesc(usuarioDto.get().getTematicas(), 1,1);

            interfazConPantalla.addAttribute("listaIntereses",listaProductos);
            interfazConPantalla.addAttribute("likes", likes);
            interfazConPantalla.addAttribute("datosProducto", productoDto.get());
            interfazConPantalla.addAttribute("respuestas", respuestas);
            interfazConPantalla.addAttribute("comentario", comentario);
            interfazConPantalla.addAttribute("datosUsuario", attr);
            return "/social/respuestasComentario";
        }



        return "/social/respuestasComentario";
    }

    //Método para borrar un comentario con Javascript
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrar(@PathVariable("id") Long id) {

        System.out.println(id);


        Optional<Comentario> coment = comentarioService.getRepo().findComentarioByIdIs(id);
        if (coment.isPresent()) {
            coment.get().setActivo(0);

            if(coment.get().getComentariosHijos() != null){
            List<Comentario> coments = coment.get().getComentariosHijos();

            for( Comentario hijo:coments){
                System.out.println(hijo.getTexto());
                hijo.setActivo(0);
                comentarioRepository.save(hijo);
            }
        }

        if(coment.get().getComentarioPadre() != null){

        }


            comentarioRepository.save(coment.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
}
