package es.geeko.web.controller;

import es.geeko.dto.ComentarioDto;
import es.geeko.service.ComentarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppReportesController extends AbstractController<ComentarioDto> {

    @GetMapping("/panelreportes")
    public String panelreporte(){
        return "/reportes/panelreportes";
    }

    @GetMapping("/reportarcomentario")
    public String reportarcomentario(){
        return "/reportes/reportarcomentario";
    }

    @GetMapping("/reportarproducto")
    public String reportarproducto(){
        return "/reportes/reportarproducto";
    }

    @GetMapping("/reportarusuario")
    public String reportarusuario(){
        return "/reportes/reportarusuario";
    }
}
