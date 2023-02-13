package es.geeko.web.controller;

import es.geeko.dto.ProductoDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.Comentario;
import es.geeko.model.Producto;
import es.geeko.model.Tematica;
import es.geeko.repository.ProductoRepository;
import es.geeko.repository.ReporteRepository;
import es.geeko.service.ComentarioService;
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
    private final ComentarioService comentarioService;
    private final ReporteRepository reporteRepository;


    public AppProductosController(ProductoService service, ProductoRepository productoRepository, UsuarioService usuarioService, TematicaService tematicaService, ComentarioService comentarioService,
                                  ReporteRepository reporteRepository) {
        this.productoService = service;
        this.productoRepository = productoRepository;
        this.usuarioService = usuarioService;
        this.tematicaService = tematicaService;
        this.comentarioService = comentarioService;
        this.reporteRepository = reporteRepository;
    }


    @GetMapping("/productos/libros")
    public String vistaLibro(ModelMap interfazConPantalla){
        final List<Producto> listaProductos = productoRepository.findProductosByLibroAndActivoAndGeekoIsOrderById(1,1,0);
        interfazConPantalla.addAttribute("listaProductos",listaProductos);
        final List<Producto> listaNovedades = productoRepository.findProductosByLibroAndActivoAndGeekoIsOrderById(1,1,1);
        interfazConPantalla.addAttribute("listaNovedades",listaNovedades);

        usuarioSesion(interfazConPantalla);

        return "/productos/libros";

    }

    @GetMapping("/productos/peliculas")
    public String vistaPelis(ModelMap interfazConPantalla){
        final List<Producto> listaProductos = productoRepository.findProductosByPeliculaAndActivoAndGeekoIsOrderById(1,1,0);
        interfazConPantalla.addAttribute("listaProductos",listaProductos);
        final List<Producto> listaNovedades = productoRepository.findProductosByPeliculaAndActivoAndGeekoIsOrderById(1,1,1);
        interfazConPantalla.addAttribute("listaNovedades",listaNovedades);

        usuarioSesion(interfazConPantalla);

        return "/productos/peliculas";
    }

    @GetMapping("/productos/series")
    public String vistaSerie(ModelMap interfazConPantalla){
        final List<Producto> listaProductos = productoRepository.findProductosBySerieAndActivoAndGeekoIsOrderById(1,1,0);
        interfazConPantalla.addAttribute("listaProductos",listaProductos);
        final List<Producto> listaNovedades = productoRepository.findProductosBySerieAndActivoAndGeekoIsOrderById(1,1,1);
        interfazConPantalla.addAttribute("listaNovedades",listaNovedades);

        usuarioSesion(interfazConPantalla);

        return "/productos/series";

    }

    @GetMapping("/productos/videojuegos")
    public String vistaVideojuegos(ModelMap interfazConPantalla){
        final List<Producto> listaProductos = productoRepository.findProductosByVideojuegoAndActivoAndGeekoIsOrderById(1,1,0);
        interfazConPantalla.addAttribute("listaProductos",listaProductos);
        final List<Producto> listaNovedades = productoRepository.findProductosByVideojuegoAndActivoAndGeekoIsOrderById(1,1,1);
        interfazConPantalla.addAttribute("listaNovedades",listaNovedades);

        usuarioSesion(interfazConPantalla);

        return "/productos/videojuegos";

    }


    @GetMapping("/productos/crearproducto")
    public String vistaCrearProducto(ModelMap interfazConPantalla){

        usuarioSesion(interfazConPantalla);

        final ProductoDto productoDto = new ProductoDto();
        productoDto.setImagen("/imagenes/noimage.jpg");
        interfazConPantalla.addAttribute("datosProducto",productoDto);

        final List<Tematica> tematicas = tematicaService.buscarEntidades();
        interfazConPantalla.addAttribute("listaTematicas",tematicas);

        return "productos/crearproducto";

    }

    @PostMapping("/productos/crearproducto")
    public String guardarProducto(ProductoDto productoDto) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        productoDto.setUsuario(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()));

        if(authentication.getAuthorities().size() > 1){
            productoDto.setGeeko(1);
        }

        if(productoDto.getActivo() == null){
            productoDto.setActivo(1);
        }

        if(productoDto.getLibro() == null){
            productoDto.setLibro(0);
        }
        if(productoDto.getVideojuego() == null){
            productoDto.setVideojuego(0);
        }
        if(productoDto.getSerie() == null){
            productoDto.setSerie(0);
        }
        if(productoDto.getPelicula() == null){
            productoDto.setPelicula(0);
        }
        if(productoDto.getLibro() == 0 && productoDto.getVideojuego() == 0 && productoDto.getSerie() == 0 && productoDto.getPelicula() == 0){
            productoDto.setLibro(1);
        }
        if(productoDto.getGeeko() == null){
            productoDto.setGeeko(0);
        }

        this.productoService.guardar(productoDto);

        return String.format("redirect:/productos/subidos");
    }

    @GetMapping("/productos/{idpro}")
    public String vistaProducto(@PathVariable("idpro") Integer id, ModelMap interfazConPantalla){

        usuarioSesion(interfazConPantalla);

        final List<Comentario> listaComentarios = this.comentarioService.getRepo().findComentariosByProductoIdAndActivoIs(id,1);
        interfazConPantalla.addAttribute("listaComentarios",listaComentarios);

        Optional<ProductoDto> producto = productoService.encuentraPorId(id);
        ProductoDto productoDtoGuardar =  producto.get();
        interfazConPantalla.addAttribute("datosProducto",productoDtoGuardar);
        return "productos/producto";
    }

    @GetMapping("/productos/edit/{idpro}")
    public String vistaEditProducto(@PathVariable("idpro") Integer id, ModelMap interfazConPantalla){

        usuarioSesion(interfazConPantalla);
        Optional<ProductoDto> productoDto = this.productoService.encuentraPorId(id);

        final List<Tematica> tematicas = tematicaService.buscarEntidades();
        interfazConPantalla.addAttribute("listaTematicas",tematicas);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        final List<Producto> listaProductos = productoRepository.findTop5ProductosByTematicaIsInAndGeekoIsAndActivoIs(usuarioDto.get().getTematicas(), 1,1);
        interfazConPantalla.addAttribute("listaProductos",listaProductos);


        ProductoDto editar = productoDto.get();

        interfazConPantalla.addAttribute("datosProducto", editar);
        System.out.println(editar.getGeeko());
        return "productos/crearproducto";
    }

    @PostMapping("/productos/edit/{idpro}")
    public String editProducto(@PathVariable("idpro") Integer id, ProductoDto productoDtoEntrada) throws Exception {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        System.out.println(productoDtoEntrada.getGeeko());
        UsuarioDto attr = usuarioDto.get();

        ProductoDto productoDtoGuardar = new ProductoDto();
        productoDtoGuardar.setId(id);

        if(productoDtoEntrada.getVideojuego() == null){
            productoDtoEntrada.setVideojuego(0);
        }
        if(productoDtoEntrada.getLibro() == null){
            productoDtoEntrada.setLibro(0);
        }
        if(productoDtoEntrada.getSerie() == null){
            productoDtoEntrada.setSerie(0);
        }
        if(productoDtoEntrada.getPelicula() == null){
            productoDtoEntrada.setPelicula(0);
        }
        if(productoDtoEntrada.getLibro() == 0 && productoDtoEntrada.getVideojuego() == 0 && productoDtoEntrada.getSerie() == 0 && productoDtoEntrada.getPelicula() == 0){
            productoDtoEntrada.setLibro(1);
        }
        if(productoDtoEntrada.getGeeko() == null){
            productoDtoEntrada.setGeeko(0);
        }
        if(productoDtoEntrada.getActivo() == null){
            productoDtoEntrada.setActivo(1);
        }


        productoDtoGuardar.setTitulo(productoDtoEntrada.getTitulo());
        productoDtoGuardar.setImagen(productoDtoEntrada.getImagen());
        productoDtoGuardar.setDescripcion(productoDtoEntrada.getDescripcion());
        productoDtoGuardar.setPrecio(productoDtoEntrada.getPrecio());
        productoDtoGuardar.setUsuario(this.usuarioService.getMapper().toEntity(attr));
        productoDtoGuardar.setPuntuacion(productoDtoEntrada.getPuntuacion());
        productoDtoGuardar.setVideojuego(productoDtoEntrada.getVideojuego());
        productoDtoGuardar.setLibro(productoDtoEntrada.getLibro());
        productoDtoGuardar.setPelicula(productoDtoEntrada.getPelicula());
        productoDtoGuardar.setSerie(productoDtoEntrada.getSerie());
        productoDtoGuardar.setReportado(productoDtoEntrada.getReportado());
        productoDtoGuardar.setActivo(productoDtoEntrada.getActivo());
        productoDtoGuardar.setFechaSubida(productoDtoEntrada.getFechaSubida());
        productoDtoGuardar.setComentario(productoDtoEntrada.getComentario());
        productoDtoGuardar.setTematica(productoDtoEntrada.getTematica());
        productoDtoGuardar.setProductosReportados(productoDtoEntrada.getProductosReportados());
        productoDtoGuardar.setGeeko(productoDtoEntrada.getGeeko());
        this.productoService.guardar(productoDtoGuardar);

        return String.format("redirect:/productos/subidos");
    }


    @GetMapping("/productos/subidos")
    public String vistaSubidos(ModelMap interfazConPantalla){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);

        final List<Producto> listaProductos = productoRepository.findProductosByUsuarioIdAndActivoIs(attr.getId(),1);
        interfazConPantalla.addAttribute("listaProductos",listaProductos);
        return "productos/productossubidos";
    }

    public void usuarioSesion(ModelMap interfazConPantalla){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario",attr);


        final List<Producto> listaIntereses = productoRepository.findTop5ProductosByTematicaIsInAndGeekoIsAndActivoIs(usuarioDto.get().getTematicas(), 1,1);
        interfazConPantalla.addAttribute("listaIntereses",listaIntereses);

    }


}
