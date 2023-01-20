package es.geeko.web.controller;

import es.geeko.dto.ProductoDto;
import es.geeko.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class APIProductosController {
    private final ProductoService service;

    public APIProductosController(ProductoService service) {
        this.service = service;
    }
    @GetMapping("")
    public  ResponseEntity<List<ProductoDto>>  vistaUsuarios( ){

        List<ProductoDto> lprodto = this.service.buscarTodos();

        if (lprodto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lprodto, HttpStatus.OK);
    }
}
