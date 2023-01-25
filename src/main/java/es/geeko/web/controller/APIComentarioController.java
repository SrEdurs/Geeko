package es.geeko.web.controller;

import es.geeko.model.Comentario;
import es.geeko.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/comentario")
public class APIComentarioController {
    @Autowired
    ComentarioRepository comentarioRepository;

    @GetMapping("/listacomentarios")
    public ResponseEntity<List<Comentario>> getAllComentarios(){
        try{
            //Generamos el contenedor
            List<Comentario> comentarios = new ArrayList<>();

            //Leemos dentro del array el contenido del modelo virtual desde el repositorio
            comentarioRepository.findAll().forEach(comentarios::add);

            //Gestionar las respuestas
            if(comentarios.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            //Hay registros
            return new ResponseEntity<>(comentarios, HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
