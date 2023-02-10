package es.geeko.web.controller;

import es.geeko.dto.ComentarioDto;
import es.geeko.dto.ProductoDto;
import es.geeko.dto.ReporteDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.Comentario;
import es.geeko.model.Producto;
import es.geeko.model.Reporte;
import es.geeko.model.Usuario;
import es.geeko.repository.ComentarioRepository;
import es.geeko.repository.ProductoRepository;
import es.geeko.repository.ReporteRepository;
import es.geeko.repository.UsuarioRepository;
import es.geeko.service.ComentarioService;
import es.geeko.service.ProductoService;
import es.geeko.service.ReporteService;
import es.geeko.service.UsuarioService;
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
public class AppReportesController extends AbstractController<ComentarioDto> {

    private UsuarioService usuarioService;
    private ReporteService reporteService;
    private ComentarioService comentarioService;
    private ProductoService productoService;
    private final ReporteRepository reporteRepository;
    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public AppReportesController(UsuarioService usuarioService, ReporteService reporteService, ComentarioService comentarioService, ProductoService productoService,
                                 ReporteRepository reporteRepository,
                                 ComentarioRepository comentarioRepository,
                                 UsuarioRepository usuarioRepository,
                                 ProductoRepository productoRepository) {
        this.usuarioService = usuarioService;
        this.reporteService = reporteService;
        this.comentarioService = comentarioService;
        this.productoService = productoService;
        this.reporteRepository = reporteRepository;
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }


    @GetMapping("/reportarcomentario/{id}")
    public String vistaReportarcomentario(@PathVariable("id") Integer id, ModelMap interfazConPantalla) throws Exception {

        final ReporteDto reporteDto = new ReporteDto();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        Optional<ComentarioDto> comentario = comentarioService.encuentraPorId(id);


        if(comentario.isPresent()) {

            ComentarioDto comen = comentario.get();
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosReporte",reporteDto);
            interfazConPantalla.addAttribute("datosUsuario", attr);
            interfazConPantalla.addAttribute("datosComentario", comen);
            return "reportes/reportarcomentario";
        } else{
            return "error";
        }
    }

    @PostMapping("/reportarcomentario/{id}")
    public String reportarComentario(@PathVariable("id") Integer id, ReporteDto reporteDto) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Comentario comentario = this.comentarioService.getRepo().findComentariosByIdIs(id);
        Reporte reporte = new Reporte();
        reporte.setComentarios(this.comentarioService.getRepo().findComentarioById(comentario.getId()));
        reporte.setUsuario(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()));
        reporte.setMotivo(reporteDto.getMotivo());
        reporteRepository.save(reporte);

        List<Reporte> reportes = new ArrayList<>();
        reportes.add(reporte);

        comentario.setComentariosReportados(reportes);
        comentario.setReportado(1);
        comentarioRepository.save(comentario);

        return String.format("redirect:/perfil");
    }

    @GetMapping("/reportarusuario/{id}")
    public String vistaReportarUsuario(@PathVariable("id") Integer id, ModelMap interfazConPantalla) throws Exception {

        final ReporteDto reporteDto = new ReporteDto();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        Optional<UsuarioDto> usuario = usuarioService.encuentraPorId(id);


        if(usuario.isPresent()) {

            UsuarioDto usu = usuario.get();
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosReporte",reporteDto);
            interfazConPantalla.addAttribute("datosUsuario", attr);
            interfazConPantalla.addAttribute("datosReportado", usu);
            return "reportes/reportarusuario";
        } else{
            return "error";
        }
    }

    @PostMapping("/reportarusuario/{id}")
    public String reportarUsuario(@PathVariable("id") Integer id, ReporteDto reporteDto) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Usuario usuario = this.usuarioService.getRepo().getUsuarioByIdIs(id);
        Reporte reporte = new Reporte();
        reporte.setUsuarios(this.usuarioService.getRepo().findUsuariosByIdIs(id));
        reporte.setUsuario(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()));
        reporte.setMotivo(reporteDto.getMotivo());
        reporteRepository.save(reporte);

        List<Reporte> reportes = new ArrayList<>();
        reportes.add(reporte);

        usuario.setUsuariosReportados(reportes);
        usuario.setReportado(1);
        usuarioRepository.save(usuario);

        return String.format("redirect:/perfil");
    }

    @GetMapping("/reportarproducto/{id}")
    public String vistaReportarProducto(@PathVariable("id") Integer id, ModelMap interfazConPantalla) throws Exception {

        final ReporteDto reporteDto = new ReporteDto();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        Optional<ProductoDto> producto = productoService.encuentraPorId(id);

        if(producto.isPresent()) {

            ProductoDto pro = producto.get();
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosReporte",reporteDto);
            interfazConPantalla.addAttribute("datosUsuario", attr);
            interfazConPantalla.addAttribute("datosReportado", pro);
            return "reportes/reportarproducto";
        } else{
            return "error";
        }
    }

    @PostMapping("/reportarproducto/{id}")
    public String reportarProducto(@PathVariable("id") Integer id, ReporteDto reporteDto) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Producto producto = this.productoService.getRepo().findProductoByIdIs(id);
        Reporte reporte = new Reporte();
        reporte.setProductos(this.productoService.getRepo().findProductosByIdIs(id));
        reporte.setUsuario(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()));
        reporte.setMotivo(reporteDto.getMotivo());
        reporteRepository.save(reporte);

        List<Reporte> reportes = new ArrayList<>();
        reportes.add(reporte);

        producto.setProductosReportados(reportes);
        producto.setReportado(1);
        productoRepository.save(producto);

        return String.format("redirect:/perfil");
    }

    @GetMapping("/panelreportes")
    public String vistaReportes(ModelMap interfazConPantalla) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());
        UsuarioDto attr = usuarioDto.get();
        interfazConPantalla.addAttribute("datosUsuario", attr);

        final List<Comentario> listaComentariosReportados = comentarioRepository.findComentariosByReportadoIs(1);
        interfazConPantalla.addAttribute("comentarios", listaComentariosReportados);

        final List<Usuario> listaUsuariosReportados = usuarioRepository.findUsuariosByReportadoIs(1);
        interfazConPantalla.addAttribute("usuarios", listaUsuariosReportados);

        final List<Producto> listaProductosReportados = productoRepository.findProductosByReportadoIs(1);
        interfazConPantalla.addAttribute("productos", listaProductosReportados);

        return "reportes/panelreportes";
    }
}
