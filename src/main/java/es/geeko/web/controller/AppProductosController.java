package es.geeko.web.controller;

import es.geeko.dto.ProductoDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppProductosController extends AbstractController<UsuarioDto> {

    private final ProductoService service;

    public AppProductosController(ProductoService service) {
        this.service = service;
    }


    @GetMapping("/productos/crearproducto")
    public String vistaRegistro(ModelMap interfazConPantalla){
        //Instancia en memoria del dto a informar en la pantalla
        final ProductoDto productoDto = new ProductoDto();
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datosProducto",productoDto);
        return "productos/crearproducto";
    }

    @PostMapping("/productos/crearproducto")
    public String guardarUsuario(ProductoDto productoDto) throws Exception {
        //LLamo al método del servicio para guardar los datos
        ProductoDto productoGuardado =  this.service.guardar(productoDto);
        System.out.println(productoGuardado.getTitulo());
        Long id = productoGuardado.getId();
        return "productos/producto";
    }


}
