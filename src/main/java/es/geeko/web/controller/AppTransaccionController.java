package es.geeko.web.controller;

import es.geeko.dto.ProductoDto;
import es.geeko.dto.TransaccionDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.*;
import es.geeko.repository.ComentarioRepository;
import es.geeko.repository.ProductoRepository;
import es.geeko.repository.TransaccionRepository;
import es.geeko.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AppTransaccionController extends AbstractController<UsuarioDto> {


    @Autowired
    private IUserService userService;
    private final UsuarioService usuarioService;


    @Autowired
    private UserDetailsService uds;
    private final ProductoRepository productoRepository;
    private final ComentarioRepository comentarioRepository;
    private final ProductoService productoService;
    private final TransaccionRepository transaccionRepository;

    public AppTransaccionController(UsuarioService usuarioService,
                                    ProductoRepository productoRepository,
                                    ComentarioRepository comentarioRepository,
                                    ProductoService productoService,
                                    TransaccionRepository transaccionRepository) {
        this.usuarioService = usuarioService;
        this.productoRepository = productoRepository;
        this.comentarioRepository = comentarioRepository;
        this.productoService = productoService;
        this.transaccionRepository = transaccionRepository;
    }

    @GetMapping("/pago/{id}")
    public String vistaPago(@PathVariable("id") Integer id , ModelMap interfazConPantalla) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        Optional<ProductoDto> producto = this.productoService.encuentraPorId(id);
        ProductoDto productoDto = producto.get();

        System.out.println(productoDto.getPrecio());

        if (usuarioDto.isPresent()){
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosProducto",productoDto);
            interfazConPantalla.addAttribute("datosUsuario",attr);
            return "productos/pago";
        } else{
            return "error";
        }
    }

    @PostMapping("/pago/{id}")
    public String pago(@PathVariable("id") Integer id) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        System.out.println(1);
        if (usuarioDto.isPresent()){
            System.out.println(12);
            UsuarioDto attr = usuarioDto.get();
            List<Usuario> usuarios = new ArrayList<>();
            System.out.println(13);
            usuarios.add(this.usuarioService.getMapper().toEntity(attr));
            Producto producto = this.productoService.getRepo().findProductoByIdIs(id);
            producto.setActivo(0);
            usuarios.add(producto.getUsuario());
            System.out.println(2);
            Transaccion transaccion = new Transaccion();
            transaccion.setIdVendedor(producto.getUsuario().getId());
            transaccion.setIdCliente(attr.getId());
            transaccion.setProducto(producto);
            transaccion.setUsuarios(usuarios);

            System.out.println(3);
            productoRepository.save(producto);
            transaccionRepository.save(transaccion);
            attr.getTransacciones().add(transaccion);
            this.usuarioService.guardar(attr);
            System.out.println(4);

            return String.format("redirect:/confirmacion");
        } else{
            return "error";
        }


    }

    @GetMapping("/confirmacion")
    public String vistaConfirmacion(ModelMap interfazConPantalla) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);
        return "productos/confirmacionPago";

    }

    @GetMapping("/pedidos")
    public String vistaPedidos(ModelMap interfazConPantalla) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
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




}
