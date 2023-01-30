package es.geeko.web.controller;

import es.geeko.dto.ProductoDto;
import es.geeko.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AppProductosController extends AbstractController<ProductoDto> {

    private final ProductoService service;

    public AppProductosController(ProductoService service) {
        this.service = service;
    }


    @GetMapping("/productos/libros")
    public String vistaLibro(){
        return "/productos/libros";
    }

    @GetMapping("/productos/peliculas")
    public String vistaPelis(){
        return "/productos/peliculas";
    }

    @GetMapping("/productos/series")
    public String vistaSerie(){
        return "/productos/series";
    }

    @GetMapping("/productos/videojuegos")
    public String vistaVideojuegos(){
        return "/productos/videojuegos";
    }


    @GetMapping("/productos/crearproducto")
    public String vistaCrearProducto(ModelMap interfazConPantalla){
        //Instancia en memoria del dto a informar en la pantalla
        final ProductoDto productoDto = new ProductoDto();
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datosProducto",productoDto);
        return "productos/crearproducto";
    }

    @PostMapping("/productos/crearproducto")
    public String guardarProducto(ProductoDto productoDto) throws Exception {
        //LLamo al método del servicio para guardar los datos
        ProductoDto productoGuardado =  this.service.guardar(productoDto);
        System.out.println("Titulo = " + productoGuardado.getTitulo());
        System.out.println("Imagen = " + productoGuardado.getImagen());
        System.out.println("Descripcion = " + productoGuardado.getDescripcion());
        Long id = productoGuardado.getId();
        return "productos/productopropio";
    }

    @PostMapping("/productos/{idusr}")
    public String guardarEdicionDatosUsuario(@PathVariable("idusr") Integer id, ProductoDto productoEntrada) throws Exception {
        //Cuidado que la password no viene
        //Necesitamos copiar la información que llega menos la password
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<ProductoDto> productoDtoControl = this.service.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (productoDtoControl.isPresent()){

            //LLamo al método del servicio para guardar los datos
            ProductoDto productoDtoGuardar =  new ProductoDto();
            productoDtoGuardar.setId(Long.valueOf(id));
            productoDtoGuardar.setTitulo(productoEntrada.getTitulo());
            productoDtoGuardar.setDescripcion(productoEntrada.getDescripcion());

            this.service.guardar(productoDtoGuardar);
            return String.format("redirect:/productos/{idusr}", id);
        } else {
            //Mostrar página usuario no existe
            return "error";
        }
    }


}
