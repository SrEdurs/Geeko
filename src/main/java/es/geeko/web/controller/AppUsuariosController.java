package es.geeko.web.controller;

import es.geeko.dto.UsuarioDto;
import es.geeko.dto.UsuariosListaDto;
import es.geeko.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class AppUsuariosController extends AbstractController<UsuarioDto> {

    private final  UsuarioService service;

    public AppUsuariosController(UsuarioService service) {
        this.service = service;
    }


    @GetMapping("")
    public String vistaHome(){
        return "index";
    }


    @GetMapping("/usuarios")
    public String vistaUsuarios(@RequestParam("page") Optional<Integer> page,
                                @RequestParam("size") Optional<Integer> size,
                                ModelMap interfazConPantalla){

        Integer pagina = 1;
        if (page.isPresent()) {
            pagina = page.get() -1;
        }
        Integer maxelementos = 10;
        if (size.isPresent()) {
            maxelementos = size.get();
        }
        Page<UsuarioDto> usuarioDtoPage =
                this.service.buscarTodos(PageRequest.of(pagina,maxelementos));
        interfazConPantalla.addAttribute(pageNumbersAttributeKey,dameNumPaginas(usuarioDtoPage));
        interfazConPantalla.addAttribute("listausuarios", usuarioDtoPage);
        return "usuarios/listausuariospagina";
    }


    @GetMapping("/usuarios/{idusr}")
    public String vistaDatosUsuario(@PathVariable("idusr") Integer id, ModelMap interfazConPantalla){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<UsuarioDto> usuarioDto = this.service.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (usuarioDto.isPresent()){
            //Como encontré datos, obtengo el objerto de tipo "UsuarioDto"
            //addAttribute y thymeleaf no entienden Optional
            UsuarioDto attr = usuarioDto.get();
            System.out.println("La password es:");
            System.out.println(attr.getClave());
            //Asigno atributos y muestro
            interfazConPantalla.addAttribute("datosUsuario",attr);
            return "usuarios/edit";
        } else{
            //Mostrar página usuario no existe
            return "usuarios/detallesusuarionoencontrado";
        }
    }


    @GetMapping("/login")
    public String vistaLogin(){
        return "/login";
    }

    @PostMapping("/login")
    public String vistaLoginy(){
        return "/login";
    }


    //Para crear un usuario hay dos bloques
    //El que genera la pantalla para pedir los datos de tipo GetMapping
    //Cuando pasamos información a la pantalla hay que usar ModelMap
    @GetMapping("/registro")
    public String vistaRegistro(ModelMap interfazConPantalla){
        //Instancia en memoria del dto a informar en la pantalla
        final UsuarioDto usuarioDto = new UsuarioDto();
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datosUsuario",usuarioDto);
        return "usuarios/registro";
    }

    //El que con los datos de la pantalla guarda la información de tipo PostMapping
    @PostMapping("/registro")
    public String guardarUsuario(UsuarioDto usuarioDto) throws Exception {
        //LLamo al método del servicio para guardar los datos
        UsuarioDto usuarioGuardado =  this.service.guardar(usuarioDto);
        Long id = usuarioGuardado.getId();
        //return "usuarios/detallesusuario";
        return "/inicio";
    }


    @PostMapping("/usuarios/{idusr}/delete")
    public String eliminarDatosUsuario(@PathVariable("idusr") Integer id){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<UsuarioDto> usuarioDto = this.service.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (usuarioDto.isPresent()){
            this.service.eliminarPorId(id);
            //Mostrar listado de usuarios
            return "redirect:/usuarios";
        } else{
            //Mostrar página usuario no existe
            return "usuarios/detallesusuarionoencontrado";
        }
    }


    //Me falta un postmaping para guardar
    @PostMapping("/usuarios/{idusr}")
    public String guardarEdicionDatosUsuario(@PathVariable("idusr") Integer id, UsuarioDto usuarioDtoEntrada) throws Exception {
        //Cuidado que la password no viene
        //Necesitamos copiar la información que llega menos la password
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<UsuarioDto> usuarioDtoControl = this.service.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (usuarioDtoControl.isPresent()){
            System.out.println("usuarioDtoControl:La password es:");
            System.out.println(usuarioDtoControl.get().getClave());

            //LLamo al método del servicioi para guardar los datos
            UsuarioDto usuarioDtoGuardar =  new UsuarioDto();
            usuarioDtoGuardar.setId(id);
            usuarioDtoGuardar.setEmilio(usuarioDtoEntrada.getEmilio());
            usuarioDtoGuardar.setUsuario(usuarioDtoEntrada.getUsuario());
            //!!!la password viene directamente de la lectura en la BBDD
            usuarioDtoGuardar.setClave(usuarioDtoControl.get().getClave());

            this.service.guardar(usuarioDtoGuardar);
            return String.format("redirect:/usuarios/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "usuarios/detallesusuarionoencontrado";
        }
    }


    // Lista múltiple de edición
    @GetMapping("/usuarios/editmultiple")
    public String mostrarEditMultipleForm(Model intefrazConPantalla) {
        UsuariosListaDto usuariosListaDto = new UsuariosListaDto(this.service.buscarTodos());

        intefrazConPantalla.addAttribute("form", usuariosListaDto);
        return "usuarios/listaeditableusuarios";
    }


    @PostMapping("/usuarios/savemultiple")
    public String saveListaUsuariuos(@ModelAttribute UsuariosListaDto usuariosListaDto) {
        service.guardar(usuariosListaDto.getUsuarioDtos());
        return "redirect:/usuarios";
    }
}
