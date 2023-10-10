package es.geeko.web.controller;
import es.geeko.dto.ComentarioDto;
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


    @GetMapping("/respuestas/{id}")
    public String respuestasComentario(@PathVariable("id") Long id, ModelMap interfazConPantalla) {

        //Usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        Optional<Comentario> comentarioOptional = comentarioRepository.findComentarioByIdIs(id);

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

            //Creamos el DTO del nuevo comentario y lo mandamos a la pantalla
            final ComentarioDto comentarioDto = new ComentarioDto();

            interfazConPantalla.addAttribute("datosComentario",comentarioDto);
            interfazConPantalla.addAttribute("listaIntereses",listaProductos);
            interfazConPantalla.addAttribute("likes", likes);
            interfazConPantalla.addAttribute("respuestas", respuestas);
            interfazConPantalla.addAttribute("comentario", comentario);
            interfazConPantalla.addAttribute("datosUsuario", attr);
            return "/social/respuestasComentario";
        }



        return "/social/respuestasComentario";
    }



    @PostMapping("/respuestas/{id}")
    public String guardarRespuestas(@PathVariable("id") Long id, ComentarioDto comentarioDto, ModelMap interfazConPantalla) {

        //Datos de usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);

        //Asignación de los datos del nuevo comentario
        Comentario comentario = new Comentario();
        comentario.setUsuario(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()));

        if (comentarioDto.getProducto() != null){
            comentario.setProducto(this.productoService.getRepo().findProductoByIdIs(comentarioService.encuentraPorIdEntity(id).get().getProducto().getId()));
        }
        comentario.setTexto(comentarioDto.getTexto());
        comentario.setActivo(1);
        comentario.setComentarioPadre(this.comentarioService.getRepo().findComentariosByIdIs(id));
        comentarioRepository.save(comentario);

        Comentario comentarioPadre = this.comentarioService.getRepo().findComentariosByIdIs(id);
        comentarioPadre.getComentariosHijos().add(comentario);
        this.comentarioService.getRepo().save(comentarioPadre);

        //Redireccionamos al producto al que pertenece el comentario
        return "redirect:/respuestas/" + id;

    }

    //Método para borrar un comentario con Javascript
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> borrar(@PathVariable("id") Long id) {

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
