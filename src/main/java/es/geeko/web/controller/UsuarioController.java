package es.geeko.web.controller;

import es.geeko.model.Usuario;
import es.geeko.repository.UsuarioRepository;
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
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;


    @GetMapping("/listausuarios")
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        try{
            //Generamos el contenedor
            List<Usuario> usuarios = new ArrayList<>();

            //Leemos dentro del array el contenido del modelo virtual desde el repositorio
            usuarioRepository.findAll().forEach(usuarios::add);

            //Gestionar las respuestas
            if(usuarios.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            //Hay registros
            return new ResponseEntity<>(usuarios, HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
