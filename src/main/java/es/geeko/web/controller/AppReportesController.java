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
import es.geeko.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final UsuarioService usuarioService;
    private final ComentarioService comentarioService;
    private final ProductoService productoService;
    private final ReporteRepository reporteRepository;
    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;

    public AppReportesController(UsuarioService usuarioService, ComentarioService comentarioService, ProductoService productoService,
                                 ReporteRepository reporteRepository,
                                 ComentarioRepository comentarioRepository,
                                 UsuarioRepository usuarioRepository,
                                 ProductoRepository productoRepository) {
        this.usuarioService = usuarioService;
        this.comentarioService = comentarioService;
        this.productoService = productoService;
        this.reporteRepository = reporteRepository;
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
    }


    @GetMapping("/reportarcomentario/{id}")
    public String vistaReportarcomentario(@PathVariable("id") Integer id, ModelMap interfazConPantalla) {

        //Creamos el DTO del nuevo reporte
        final ReporteDto reporteDto = new ReporteDto();

        //Datos del usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Datos del comentario reportado
        Optional<ComentarioDto> comentario = comentarioService.encuentraPorId(id);

        //Si el usuario de la sesión y el comentario están presentes, mostramos por pantalla
        if(usuarioDto.isPresent() && comentario.isPresent()) {

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
    public String reportarComentario(@PathVariable("id") Integer id, ReporteDto reporteDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        //Asignamos los datos al reporte del comentario
        Comentario comentario = this.comentarioService.getRepo().findComentariosByIdIs(id);
        Reporte reporte = new Reporte();
        reporte.setComentarios(this.comentarioService.getRepo().findComentarioById(comentario.getId()));

        //Reporta el usuario de la sesión
        reporte.setUsuario(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()));
        reporte.setMotivo(reporteDto.getMotivo());
        reporteRepository.save(reporte);

        //Actualizamos los datos del comentario
        List<Reporte> reportes = new ArrayList<>();
        reportes.add(reporte);
        comentario.setComentariosReportados(reportes);
        comentario.setReportado(1);
        comentarioRepository.save(comentario);

        return "redirect:/productos/" + comentario.getProducto().getId();
    }

    @GetMapping("/reportarusuario/{id}")
    public String vistaReportarUsuario(@PathVariable("id") Integer id, ModelMap interfazConPantalla) {

        //Creamos el DTO del nuevo reporte
        final ReporteDto reporteDto = new ReporteDto();

        //Datos del usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Datos del usuario a reportar
        Optional<UsuarioDto> usuarioRep = usuarioService.encuentraPorId(id);

        //Si el usuario a reportar y el de la sesión están presentes, mostramos por pantalla
        if(usuarioRep.isPresent() && usuarioDto.isPresent()) {

            UsuarioDto usu = usuarioRep.get();
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
    public String reportarUsuario(@PathVariable("id") Integer id, ReporteDto reporteDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        //Asignamos los datos al reporte del usuario
        Usuario usuario = this.usuarioService.getRepo().getUsuarioByIdIs(id);
        Reporte reporte = new Reporte();
        reporte.setUsuarios(this.usuarioService.getRepo().findUsuariosByIdIs(id));

        //Reporta el usuario de la sesión
        reporte.setUsuario(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()));
        reporte.setMotivo(reporteDto.getMotivo());
        reporteRepository.save(reporte);

        //Actualizamos los datos del usuario
        List<Reporte> reportes = new ArrayList<>();
        reportes.add(reporte);
        usuario.setUsuariosReportados(reportes);
        usuario.setReportado(1);
        usuarioRepository.save(usuario);

        return "redirect:/perfil";
    }

    @GetMapping("/reportarproducto/{id}")
    public String vistaReportarProducto(@PathVariable("id") Integer id, ModelMap interfazConPantalla) {

        //Creamos el DTO del nuevo reporte
        final ReporteDto reporteDto = new ReporteDto();

        //Datos del usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Datos del producto a reportar
        Optional<ProductoDto> producto = productoService.encuentraPorId(id);

        //Si el producto a reportar y el de la sesión están presentes, mostramos por pantalla
        if(producto.isPresent() && usuarioDto.isPresent()) {

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

        //Asignamos los datos al reporte del producto
        Producto producto = this.productoService.getRepo().findProductoByIdIs(id);
        Reporte reporte = new Reporte();
        reporte.setProductos(this.productoService.getRepo().findProductosByIdIs(id));

        //Reporta el usuario de la sesión
        reporte.setUsuario(this.usuarioService.getRepo().getUsuarioByIdIs(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId()));
        reporte.setMotivo(reporteDto.getMotivo());
        reporteRepository.save(reporte);

        //Actualizamos los datos del producto
        List<Reporte> reportes = new ArrayList<>();
        reportes.add(reporte);
        producto.setProductosReportados(reportes);
        producto.setReportado(1);
        productoRepository.save(producto);

        return "redirect:/perfil";
    }

    @GetMapping("/panelreportes")
    public String vistaReportes(ModelMap interfazConPantalla) {

        //Datos del usuario de la sesión
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(this.usuarioService.getRepo().findUsuarioByEmilio(username).get().getId());

        //Si el usuario está presente, mostramos por pantalla
        if(usuarioDto.isPresent()) {

            //Usuario de la sesión
            UsuarioDto attr = usuarioDto.get();
            interfazConPantalla.addAttribute("datosUsuario", attr);

            //Lista de comentarios reportados
            final List<Comentario> listaComentariosReportados = comentarioRepository.findComentariosByReportadoIsAndActivoIs(1, 1);
            interfazConPantalla.addAttribute("comentarios", listaComentariosReportados);

            //Lista de usuarios reportados
            final List<Usuario> listaUsuariosReportados = usuarioRepository.findUsuariosByReportadoIsAndActivoIs(1, 1);
            interfazConPantalla.addAttribute("usuarios", listaUsuariosReportados);

            //Lista de productos reportados
            final List<Producto> listaProductosReportados = productoRepository.findProductosByReportadoIsAndActivoIs(1, 1);
            interfazConPantalla.addAttribute("productos", listaProductosReportados);

            return "reportes/panelreportes";
        } else{
            return "error";
        }
    }

    //Método para descartar un reporte
    @GetMapping("/cambiareporte/{id}")
    public ResponseEntity<String> cambiaReporte(@PathVariable("id") Integer id){

        //Opcional de los 3 tipos de reportes
        Optional<Comentario> coment = comentarioService.encuentraPorIdEntity(id);
        Optional<Usuario> usu = usuarioService.encuentraPorIdEntity(id);
        Optional<Producto> pro = productoService.encuentraPorIdEntity(id);

        //Si es un comentario
        if(coment.isPresent()){
            coment.get().setReportado(0);
            coment.get().setComentariosReportados(null);
            comentarioRepository.save(coment.get());
        }

        //Si es un usuario
        if(usu.isPresent()){
            usu.get().setReportado(0);
            usu.get().setUsuariosReportados(null);
            usuarioRepository.save(usu.get());
        }

        //Si es un producto
        if(pro.isPresent()){
            pro.get().setReportado(0);
            pro.get().setProductosReportados(null);
            productoRepository.save(pro.get());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Método para suspender entidades con Javascript
    @GetMapping("/suspender/{id}")
    public ResponseEntity<String> borrar(@PathVariable("id") Integer id){

        //Opcional de los 3 tipos de datos
        Optional<Comentario> coment = comentarioService.encuentraPorIdEntity(id);
        Optional<Usuario> usu = usuarioService.encuentraPorIdEntity(id);
        Optional<Producto> pro = productoService.encuentraPorIdEntity(id);

        //Si es un comentario
        if(coment.isPresent()){
            coment.get().setActivo(0);
            comentarioRepository.save(coment.get());
        }

        //Si es un usuario
        if(usu.isPresent()){
            usu.get().setActivo(0);
            usu.get().setRoles(null);
            usuarioRepository.save(usu.get());
        }

        //Si es un producto
        if(pro.isPresent()){
            pro.get().setActivo(0);
            productoRepository.save(pro.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
