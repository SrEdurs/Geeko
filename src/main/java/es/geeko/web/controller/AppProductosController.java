package es.geeko.web.controller;

import es.geeko.dto.ProductoDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.Comentario;
import es.geeko.model.Producto;
import es.geeko.model.Tematica;
import es.geeko.repository.ProductoRepository;
import es.geeko.service.ProductoService;
import es.geeko.service.TematicaService;
import es.geeko.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AppProductosController extends AbstractController<ProductoDto> {

    private final ProductoService productoService;
    private final ProductoRepository productoRepository;
    private final UsuarioService usuarioService;
    private final TematicaService tematicaService;


    public AppProductosController(ProductoService service, ProductoRepository productoRepository, UsuarioService usuarioService, TematicaService tematicaService) {
        this.productoService = service;
        this.productoRepository = productoRepository;
        this.usuarioService = usuarioService;
        this.tematicaService = tematicaService;
    }


    @GetMapping("/productos/libros")
    public String vistaLibro(ModelMap interfazConPantalla){
        final List<Producto> listaProductos = productoRepository.findProductosByLibroAndActivoAndGeekoIs(1,1,0);
        interfazConPantalla.addAttribute("listaProductos",listaProductos);
        final List<Producto> listaNovedades = productoRepository.findProductosByLibroAndActivoAndGeekoIs(1,1,1);
        interfazConPantalla.addAttribute("listaNovedades",listaNovedades);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        if(usuarioDto.isPresent()) {
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario", attr);
            return "/productos/libros";
        } else{
            return "error";
        }
    }

    @GetMapping("/productos/peliculas")
    public String vistaPelis(ModelMap interfazConPantalla){
        final List<Producto> listaProductos = productoRepository.findProductosByPeliculaAndActivoAndGeekoIs(1,1,0);
        interfazConPantalla.addAttribute("listaProductos",listaProductos);
        final List<Producto> listaNovedades = productoRepository.findProductosByPeliculaAndActivoAndGeekoIs(1,1,1);
        interfazConPantalla.addAttribute("listaNovedades",listaNovedades);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        if(usuarioDto.isPresent()) {
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario",attr);
            return "/productos/peliculas";
        } else{
            return "error";
        }
    }

    @GetMapping("/productos/series")
    public String vistaSerie(ModelMap interfazConPantalla){
        final List<Producto> listaProductos = productoRepository.findProductosBySerieAndActivoAndGeekoIs(1,1,0);
        interfazConPantalla.addAttribute("listaProductos",listaProductos);
        final List<Producto> listaNovedades = productoRepository.findProductosBySerieAndActivoAndGeekoIs(1,1,1);
        interfazConPantalla.addAttribute("listaNovedades",listaNovedades);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        if(usuarioDto.isPresent()) {
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario",attr);
            return "/productos/series";
        } else{
            return "error";
        }
    }

    @GetMapping("/productos/videojuegos")
    public String vistaVideojuegos(ModelMap interfazConPantalla){
        final List<Producto> listaProductos = productoRepository.findProductosByVideojuegoAndActivoAndGeekoIs(1,1,0);
        interfazConPantalla.addAttribute("listaProductos",listaProductos);
        final List<Producto> listaNovedades = productoRepository.findProductosByVideojuegoAndActivoAndGeekoIs(1,1,1);
        interfazConPantalla.addAttribute("listaNovedades",listaNovedades);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        if(usuarioDto.isPresent()) {
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario",attr);
            return "/productos/videojuegos";
        } else{
            return "error";
        }
    }


    @GetMapping("/productos/crearproducto")
    public String vistaCrearProducto(ModelMap interfazConPantalla){

        final ProductoDto productoDto = new ProductoDto();
        productoDto.setImagen("/imagenes/noimage.jpg");

        interfazConPantalla.addAttribute("datosProducto",productoDto);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        if(usuarioDto.isPresent()) {
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario",attr);

            final List<Producto> listaProductos = productoRepository.findProductosByTituloIsNotLikeAndGeekoIs("prueba",1);
            interfazConPantalla.addAttribute("listaProductos",listaProductos);

            final List<Tematica> tematicas = tematicaService.buscarEntidades();
            interfazConPantalla.addAttribute("listaTematicas",tematicas);

            return "productos/crearproducto";
        } else{
            return "error";
        }
    }

    @PostMapping("/productos/crearproducto")
    public String guardarProducto(ProductoDto productoDto) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        System.out.println(productoDto.getLibro());
        productoDto.setUsuario(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()));

        if(authentication.getAuthorities().size() > 1){
            productoDto.setGeeko(1);
        }

        this.productoService.guardar(productoDto);

        return String.format("redirect:/productos/subidos");
    }

    @GetMapping("/productos/{idpro}")
    public String vistaProducto(@PathVariable("idpro") Integer id, ModelMap interfazConPantalla) throws Exception{

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);

        final List<Comentario> listaComentarios = this.productoService.encuentraPorId(id).get().getComentario();
        interfazConPantalla.addAttribute("listaComentarios",listaComentarios);

        final List<Producto> listaProductos = productoRepository.findProductosByTituloIsNotLikeAndGeekoIs("prueba",1);
        interfazConPantalla.addAttribute("listaProductos",listaProductos);

        Optional<ProductoDto> producto = productoService.encuentraPorId(id);
        ProductoDto productoDtoGuardar =  producto.get();
        interfazConPantalla.addAttribute("datosProducto",productoDtoGuardar);
        return "productos/producto";
    }

    @PostMapping("/productos/{idpro}")
    public String guardarEdicionDatosUsuario(@PathVariable("idpro") Integer id, ProductoDto productoEntrada) throws Exception {

        Optional<ProductoDto> productoDtoControl = this.productoService.encuentraPorId(id);
        if (productoDtoControl.isPresent()){

            ProductoDto productoDtoGuardar =  new ProductoDto();
            productoDtoGuardar.setId(id);
            productoDtoGuardar.setTitulo(productoEntrada.getTitulo());
            productoDtoGuardar.setDescripcion(productoEntrada.getDescripcion());

            this.productoService.guardar(productoDtoGuardar);
            return String.format("redirect:/productos/{idusr}", id);
        } else {
            return "error";
        }
    }

    @GetMapping("/productos/subidos")
    public String vistaSubidos(ModelMap interfazConPantalla){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);

        final List<Producto> listaProductos = productoRepository.findProductosByUsuarioId(attr.getId());
        interfazConPantalla.addAttribute("listaProductos",listaProductos);
        return "productos/productossubidos";
    }


}
