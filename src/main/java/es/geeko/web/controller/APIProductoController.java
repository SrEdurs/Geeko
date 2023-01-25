package es.geeko.web.controller;

import es.geeko.model.Producto;
import es.geeko.repository.ProductoRepository;
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
@RequestMapping("/api/producto")
public class APIProductoController {
    @Autowired
    ProductoRepository productoRepository;


    @GetMapping("/listaproductos")
    public ResponseEntity<List<Producto>> getAllProductos(){
        try{
            //Generamos el contenedor
            List<Producto> productos = new ArrayList<>();

            //Leemos dentro del array el contenido del modelo virtual desde el repositorio
            productoRepository.findAll().forEach(productos::add);

            //Gestionar las respuestas
            if(productos.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            //Hay registros
            return new ResponseEntity<>(productos, HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
