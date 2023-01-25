package es.geeko.web.controller;

import es.geeko.dto.ComentarioDto;
import es.geeko.dto.LoginDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.service.ComentarioService;
import es.geeko.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AppComentariosController extends AbstractController<ComentarioDto> {

    private final ComentarioService service;

    public AppComentariosController(ComentarioService service) {
        this.service = service;
    }


    @GetMapping("/social/crearcomentario")
    public String vistaCrearComentario(ModelMap interfazConPantalla){
        //Instancia en memoria del dto a informar en la pantalla
        final ComentarioDto comentarioDto = new ComentarioDto();
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datosComentario",comentarioDto);
        return "/social/crearcomentario";
    }

    @PostMapping("/social/crearcomentario")
    public String guardarComentario(ComentarioDto comentarioDto) throws Exception {
        //LLamo al método del servicio para guardar los datos
        ComentarioDto comentarioGuardado =  this.service.guardar(comentarioDto);
        Long id = comentarioGuardado.getId();
        return "usuarios/cuestionario";
    }



}
