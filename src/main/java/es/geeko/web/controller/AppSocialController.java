package es.geeko.web.controller;

import es.geeko.dto.UsuarioDto;
import es.geeko.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import es.geeko.model.Chat;
import es.geeko.model.Usuario;
import es.geeko.repository.ChatRepository;
import es.geeko.repository.UsuarioRepository;

@Controller
public class AppSocialController extends AbstractController<UsuarioDto> {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final ChatRepository chatRepository;
    private final ChatService chatService;



    public AppSocialController(UsuarioService usuarioService, UsuarioRepository usuarioRepository, ChatRepository chatRepository, ChatService chatService) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
        this.chatRepository = chatRepository;
        this.chatService = chatService;
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
    public ResponseEntity<String> noseguir(@PathVariable("id") Long id){
        System.out.println("EYYYYYYYYYYYYYYYYYYYYYYY");
        //Obtenemos el DTO del usuario actual por ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        
        Optional<Usuario> usuario = usuarioService.encuentraPorIdEntity(id);

        if(usuario.isPresent()){

            Usuario attr = this.usuarioService.getMapper().toEntity(usuarioDto.get());

            usuario.get().getSeguidos().remove(attr);

            usuarioRepository.save(usuario.get());

            System.out.println("EYOO");

            attr.getSeguimientos().remove(usuario.get());

            usuarioRepository.save(usuario.get());
            usuarioRepository.save(attr);
            System.out.println("EYYYYYYYYYYYYYYYYYYYYYYY");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/chat/{id}")
    public ResponseEntity<String> chat(@PathVariable("id") Long id){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        
        Optional<Usuario> usuario = usuarioService.encuentraPorIdEntity(id);
        Optional<Chat> chatop = chatRepository.findChatByIdDestinatarioAndIdRemitente(id,usuarioDto.get().getId());

        if(usuario.isPresent()){

            if(!chatop.isPresent()){

            Chat chat = new Chat();
            chat.setIdDestinatario(id);
            chat.setIdRemitente(usuarioDto.get().getId());
            chat.setImagen(usuario.get().getAvatar());
            chat.setTitulo(usuario.get().getNick());
            chat.setActivo(1);
            chatRepository.save(chat);

            Usuario attr = this.usuarioService.getMapper().toEntity(usuarioDto.get());
            attr.getChats().add(chat);
            usuarioRepository.save(attr);

            Usuario mensajeado = usuario.get();
            chat.setTitulo(attr.getNick());
            mensajeado.getChats().add(chat);
            usuarioRepository.save(mensajeado);
            }

            else{
                System.out.println("---------------------------- Este chat ya existe ----------------------------");
                System.out.println("---------------------------- Este chat ya existe ----------------------------");
                System.out.println("---------------------------- Este chat ya existe ----------------------------");

                
            }  
        }

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
        
        Optional<Usuario> usuario = usuarioService.encuentraPorIdEntity(id);

        if(usuario.isPresent() && usuarioDto.isPresent()){

            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario", attr);

            Usuario usu = usuario.get();
            interfazConPantalla.addAttribute("datosUsuario2", usu);
        }

        return "/social/chat";
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
