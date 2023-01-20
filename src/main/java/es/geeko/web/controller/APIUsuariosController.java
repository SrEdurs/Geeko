package es.geeko.web.controller;

import es.geeko.dto.UsuarioDto;
import es.geeko.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class APIUsuariosController {
    private final UsuarioService service;

    public APIUsuariosController(UsuarioService service) {
        this.service = service;
    }
    @GetMapping("")
    public  ResponseEntity<List<UsuarioDto>>  vistaUsuarios( ){

        List<UsuarioDto> lusrdto = this.service.buscarTodos();

        if (lusrdto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lusrdto, HttpStatus.OK);
    }
}
