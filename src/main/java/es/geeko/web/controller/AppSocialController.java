package es.geeko.web.controller;
import es.geeko.dto.MensajeDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.geeko.model.Chat;
import es.geeko.model.Comentario;
import es.geeko.model.Like;
import es.geeko.model.Mensaje;
import es.geeko.model.Producto;
import es.geeko.model.Puntuacion;
import es.geeko.model.Usuario;
import es.geeko.repository.ChatRepository;
import es.geeko.repository.ComentarioRepository;
import es.geeko.repository.LikeRepository;
import es.geeko.repository.MensajeRepository;
import es.geeko.repository.UsuarioRepository;

@Controller
public class AppSocialController extends AbstractController<UsuarioDto> {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final ChatRepository chatRepository;
    private final ChatService chatService;
    private final MensajeRepository mensajeRepository;
    private final ComentarioRepository comentarioRepository;
    private final LikeRepository likeRepository;
    private final ProductoService productoService;
    private final PuntuacionService puntuacionService;



    public AppSocialController(UsuarioService usuarioService, UsuarioRepository usuarioRepository, ChatRepository chatRepository, ChatService chatService, MensajeRepository mensajeRepository, ComentarioRepository comentarioRepository, LikeRepository likeRepository, ProductoService productoService, PuntuacionService puntuacionService) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.chatRepository = chatRepository;
        this.chatService = chatService;
        this.mensajeRepository = mensajeRepository;
        this.comentarioRepository = comentarioRepository;
        this.likeRepository = likeRepository;
        this.productoService = productoService;
        this.puntuacionService = puntuacionService;
    }

    @GetMapping("/social")
    public String social(ModelMap interfazConPantalla){

        usuarioSesionSocial(interfazConPantalla);


        return "/social/social";
    }

    @GetMapping("/seguir/{id}")
    public ResponseEntity<String> seguir(@PathVariable("id") Long id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        
        Optional<Usuario> usuario = usuarioService.encuentraPorIdEntity(id);

        if(usuario.isPresent()){

            Usuario attr = this.usuarioService.getMapper().toEntity(usuarioDto.get());
            usuario.get().getSeguidos().add(attr);
            usuarioRepository.save(usuario.get());

            attr.getSeguimientos().add(usuario.get());
            //usuarioRepository.save(attr);
            System.out.println("EOOOOOOOOOOOOOOOOOOOOOOOO");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/noseguir/{id}")
public ResponseEntity<String> noseguir(@PathVariable("id") Long id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        
    Optional<Usuario> usuario = usuarioService.encuentraPorIdEntity(id);

    if (usuario.isPresent()) {
        Usuario attr = this.usuarioService.getMapper().toEntity(usuarioDto.get());

        usuario.get().getSeguidos().remove(attr);
        usuarioRepository.save(usuario.get());

        attr.getSeguimientos().remove(usuario.get());
        usuarioRepository.save(attr);

    }

    return new ResponseEntity<>(HttpStatus.OK);
}


    @GetMapping("/chat/{id}")
    public ResponseEntity<String> chat(@PathVariable("id") Long id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        
        Optional<Usuario> usuario = usuarioService.encuentraPorIdEntity(id);

        Optional<Chat> chatop1 = chatRepository.findChatByIdDestinatarioAndIdRemitente(id,usuarioDto.get().getId());
        Optional<Chat> chatop2 = chatRepository.findChatByIdDestinatarioAndIdRemitente(usuarioDto.get().getId(), id);

        Optional<Chat> chatop = chatop1.isPresent() ? chatop1 : chatop2;

        if(chatop.isPresent()) {

            return new ResponseEntity<>(HttpStatus.OK);
        }

        List<Usuario> usuariosChat = new java.util.ArrayList<>();
        usuariosChat.add(usuario.get());
        usuariosChat.add(usuarioService.getMapper().toEntity(usuarioDto.get()));

        List<Mensaje> mensajes = new java.util.ArrayList<>();
        Chat chat = new Chat();
        chat.setIdDestinatario(id);
        chat.setIdRemitente(usuarioDto.get().getId());
        chat.setImagen(usuario.get().getAvatar());
        chat.setActivo(1);
        chat.setTitulo(usuario.get().getNick());
        chat.setUsuarios(usuariosChat);
        chat.setMensajes(mensajes);
        chatRepository.save(chat);

        Usuario attr = this.usuarioService.getMapper().toEntity(usuarioDto.get());
        attr.getChats().add(chat);
        usuarioRepository.save(attr);
        usuario.get().getChats().add(chat);
        usuarioRepository.save(usuario.get());

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/borrarchat/{id}")
    public ResponseEntity<String> borrarchat(@PathVariable("id") Long id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        
        Optional<Chat> chat = chatService.encuentraPorIdEntity(id);

        if(chat.isPresent() && usuarioDto.isPresent()){

            Usuario attr = this.usuarioService.getMapper().toEntity(usuarioDto.get());
            attr.getChats().remove(chat.get());
            usuarioRepository.save(attr);

            Usuario usu = usuarioRepository.getUsuarioByIdIs(chat.get().getIdDestinatario());
            usu.getChats().remove(chat.get());
            usuarioRepository.save(usu);

            chatRepository.delete(chat.get());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/chat/id/{id}")
        public String vistaChat(@PathVariable("id") Long id, ModelMap interfazConPantalla){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        Optional<Usuario> usuario = usuarioService.encuentraPorIdEntity(chatService.encuentraPorIdEntity(id).get().getIdDestinatario());
        Optional<Chat> chat = chatService.encuentraPorIdEntity(id);

        final MensajeDto mensajeDto = new MensajeDto();
        mensajeDto.setChat(chat.get());
        interfazConPantalla.addAttribute("mensaje", mensajeDto);

        if(usuario.isPresent() && usuarioDto.isPresent()){

            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario", attr);

            Usuario usu = usuario.get();
            interfazConPantalla.addAttribute("datosUsuario2", usu);

            Chat chat2 = chat.get();
            interfazConPantalla.addAttribute("chat", chat2);
            
            
        }

        return "/social/chat";
    }

    //postmapping para enviar mensajes
    @PostMapping("/chat/id/{id}")
    public String crearmensaje(@PathVariable("id") Long id, ModelMap interfazConPantalla, MensajeDto mensajeDto){

        System.out.println("EYOOO");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);

        System.out.println("EYOOO");

        //Asignación de los datos al mensaje
        Mensaje mensaje = new Mensaje();
        mensaje.setChat(chatService.encuentraPorIdEntity(id).get());
        mensaje.setTexto(mensajeDto.getTexto());
        mensaje.setUsuarioRemitente(usuarioService.getMapper().toEntity(usuarioDto.get()));
        mensajeRepository.save(mensaje);


        return "redirect:/chat/id/" + id;
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
    public ResponseEntity<String> noMeGusta(@PathVariable("id") Long id) {
        Optional<Comentario> comentarioOptional = comentarioRepository.findComentarioByIdIs(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDtoOptional = usuarioService.encuentraPorId(usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        if (comentarioOptional.isPresent() && usuarioDtoOptional.isPresent()) {
            Comentario comentario = comentarioOptional.get();
            UsuarioDto usuarioDto = usuarioDtoOptional.get();

            List<Like> likeses = comentario.getLikesComen();
            Optional<Like> likeOptional = likeses.stream()
                    .filter(like -> like.getUsuarioLike().getId() == (usuarioDto.getId()))
                    .findFirst();

            if (likeOptional.isPresent()) {
                Like like = likeOptional.get();
                comentario.setLikes(comentario.getLikes() - 1);
                likeRepository.delete(like);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

    // @GetMapping("/puntuar/{id}") para guardar la puntuación de un producto

    @GetMapping("/puntuar/{id}/{valor}")
    public ResponseEntity<String> puntuar(@PathVariable("id") Long id, @PathVariable("valor") String valor) {
        //Localizamos el producto a valorar
        Optional<Producto> productoOptional = productoService.getRepo().findById(id);

        //Localizamos el usuario que valora
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDtoOptional = usuarioService.encuentraPorId(usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Si ambos existen, procedemos a valorar
        if(productoOptional.isPresent() && usuarioDtoOptional.isPresent()){

            //Comprobamos que el usuario no haya valorado ya el producto
            List<Puntuacion> puntuaciones = productoOptional.get().getPuntuacionProducto();
            Optional<Puntuacion> puntuacionOptional = puntuaciones.stream()
                    .filter(puntuacion -> puntuacion.getUsuarioPuntua().getId() == (usuarioDtoOptional.get().getId()))
                    .findFirst();

            //Si el usuario no ha valorado el producto, procedemos a valorar
            if(!puntuacionOptional.isPresent()){
            //Creamos el elemento Puntuacion
            Puntuacion puntuacion = new Puntuacion();
            puntuacion.setProductoPuntuado(productoOptional.get());
            puntuacion.setUsuarioPuntua(usuarioService.getMapper().toEntity(usuarioDtoOptional.get()));

            puntuacion.setPuntuacion(Double.parseDouble(valor));
            puntuacionService.getRepo().save(puntuacion);

            //Actualizamos la puntuación media del producto
            Producto producto = productoOptional.get();
            puntuaciones.add(puntuacion);
            producto.setPuntuacionProducto(puntuaciones);

            //Calculamos la media
            Double media = 0.0;
            for(Puntuacion puntuacion1 : puntuaciones){
                media += puntuacion1.getPuntuacion();
            }
            media = media / puntuaciones.size();
            producto.setPuntuacionMedia(media);
            productoService.getRepo().save(producto);

            Usuario usuario = usuarioService.getMapper().toEntity(usuarioDtoOptional.get());
            if(usuario.getPuntuaciones() == null) usuario.setPuntuaciones(new ArrayList<>());
            usuario.getPuntuaciones().add(puntuacion);
            usuarioService.getRepo().save(usuario);
            System.out.println("Se ha guardado una nueva puntuación");

            } else{
                //Reescribimos la puntuación existente
                Puntuacion puntuacion = puntuacionOptional.get();
                puntuacion.setPuntuacion(Double.parseDouble(valor));
                puntuacionService.getRepo().save(puntuacion);

                Producto producto = productoOptional.get();

                Double media = 0.0;
                for(Puntuacion puntuacion1 : puntuaciones){
                    media += puntuacion1.getPuntuacion();
                }
                media = media / puntuaciones.size();

                //Redondear la media a 2 decimales
                media = Math.round(media * 100.0) / 100.0;

                producto.setPuntuacionMedia(media);
                productoService.getRepo().save(producto);

                System.out.println("Puntuación cambiada");
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/respuestas/{id}")
    public String respuestasComentario(@PathVariable("id") Long id, ModelMap interfazConPantalla) {
        
        Optional<Comentario> comentarioOptional = comentarioRepository.findComentarioByIdIs(id);

        if (comentarioOptional.isPresent()) {
            Comentario comentario = comentarioOptional.get();
            List<Comentario> respuestas = comentario.getComentariosHijos();
            interfazConPantalla.addAttribute("respuestas", respuestas);
            interfazConPantalla.addAttribute("comentario", comentario);
            return "/social/respuestasComentario";
        }



        return "/social/respuestasComentario";
    }

    public void usuarioSesionSocial(ModelMap interfazConPantalla){

        //Obtenemos el DTO del usuario actual por ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Si está presente, mostramos sus datos
        if(usuarioDto.isPresent()) {
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario", attr);

            List<Chat> chats = attr.getChats();
            interfazConPantalla.addAttribute("chats", chats);
            
        }
    }
}
