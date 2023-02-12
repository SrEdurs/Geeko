package es.geeko.web.controller;

import es.geeko.dto.ProductoDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.Comentario;
import es.geeko.model.Producto;
import es.geeko.model.Tematica;
import es.geeko.model.Usuario;
import es.geeko.repository.ComentarioRepository;
import es.geeko.repository.ProductoRepository;
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

    public AppTransaccionController(UsuarioService usuarioService,
                                    ProductoRepository productoRepository,
                                    ComentarioRepository comentarioRepository,
                                    ProductoService productoService) {
        this.usuarioService = usuarioService;
        this.productoRepository = productoRepository;
        this.comentarioRepository = comentarioRepository;
        this.productoService = productoService;
    }

    @GetMapping("/pago/{id}")
    public String register(@PathVariable("id") Integer id , ModelMap interfazConPantalla) {
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




}
