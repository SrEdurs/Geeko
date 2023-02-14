package es.geeko.web.controller;

import es.geeko.dto.ProductoDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.*;
import es.geeko.repository.ProductoRepository;
import es.geeko.repository.TransaccionRepository;
import es.geeko.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AppTransaccionController extends AbstractController<UsuarioDto> {


    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final ProductoRepository productoRepository;
    private final ProductoService productoService;
    private final TransaccionRepository transaccionRepository;

    public AppTransaccionController(UsuarioService usuarioService,
                                    ProductoRepository productoRepository,
                                    ProductoService productoService,
                                    TransaccionRepository transaccionRepository) {
        this.usuarioService = usuarioService;
        this.productoRepository = productoRepository;
        this.productoService = productoService;
        this.transaccionRepository = transaccionRepository;
    }

    @GetMapping("/pago/{id}")
    public String vistaPago(@PathVariable("id") Integer id , ModelMap interfazConPantalla) {

        //DTO del producto a comprar
        Optional<ProductoDto> producto = this.productoService.encuentraPorId(id);

        //Si está presente mostramos los datos
        if (producto.isPresent()){

            ProductoDto productoDto = producto.get();

            usuarioSesion(interfazConPantalla);
            interfazConPantalla.addAttribute("datosProducto",productoDto);

            return "productos/pago";

        } else{
            return "error";
        }
    }

    @PostMapping("/pago/{id}")
    public String pago(@PathVariable("id") Integer id) throws Exception {

        //DTO del usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Si está presente, establecemos los campos de la transacción
        if (usuarioDto.isPresent()){

            //Añadimos al usuario actual
            UsuarioDto attr = usuarioDto.get();
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(this.usuarioService.getMapper().toEntity(attr));

            //Añadimos al usuario propietario del producto y marcamos el producto como inactivo
            Producto producto = this.productoService.getRepo().findProductoByIdIs(id);
            producto.setActivo(0);
            usuarios.add(producto.getUsuario());

            //Asignamos los datos
            Transaccion transaccion = new Transaccion();
            transaccion.setIdVendedor(producto.getUsuario().getId());
            transaccion.setIdCliente(attr.getId());
            transaccion.setProducto(producto);
            transaccion.setUsuarios(usuarios);

            //Guardamos
            productoRepository.save(producto);
            transaccionRepository.save(transaccion);

            //Añadimos la transacción a los datos del usuario actual
            attr.getTransacciones().add(transaccion);
            this.usuarioService.guardar(attr);

            return "redirect:/confirmacion";

        } else{
            return "error";
        }


    }

    @GetMapping("/confirmacion")
    public String vistaConfirmacion(ModelMap interfazConPantalla) {

        usuarioSesion(interfazConPantalla);

        return "productos/confirmacionPago";

    }

    @GetMapping("/pedidos")
    public String vistaPedidos(ModelMap interfazConPantalla) {

        //Necesitamos el DTO del usuario
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Si está presente, mostramos sus datos y sus transacciones
        if(usuarioDto.isPresent()) {

            UsuarioDto attr = usuarioDto.get();
            List <Transaccion> transacciones = attr.getTransacciones();

            interfazConPantalla.addAttribute("pedidos", transacciones);
            interfazConPantalla.addAttribute("datosUsuario", attr);

            return "productos/pedidos";

        } else{
            return "error";
        }

    }


    public void usuarioSesion(ModelMap interfazConPantalla){

        //Obtenemos el DTO del usuario actual por ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Si está presente, mostramos sus datos
        if(usuarioDto.isPresent()) {
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario", attr);
        }


    }


}
