package es.geeko.web.controller;

import es.geeko.dto.LoginDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class AppUsuariosController extends AbstractController<UsuarioDto> {

    private final  UsuarioService service;

    public AppUsuariosController(UsuarioService service) {
        this.service = service;
    }


    @GetMapping("/home")
    public String vistaHome(){
        return "index";
    }





    @GetMapping("/usuarios/crearcuenta")
    public String vistaRegistro(ModelMap interfazConPantalla){
        //Instancia en memoria del dto a informar en la pantalla
        final UsuarioDto usuarioDto = new UsuarioDto();
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datosUsuario",usuarioDto);
        return "usuarios/crearcuenta";
    }

    @PostMapping("/usuarios/crearcuenta")
    public String guardarUsuario(UsuarioDto usuarioDto) throws Exception {
        //LLamo al método del servicio para guardar los datos
        UsuarioDto usuarioGuardado =  this.service.guardar(usuarioDto);
        System.out.println(usuarioGuardado.getClave());
        Long id = usuarioGuardado.getId();
        return "usuarios/cuestionario";
    }

    @GetMapping("/usuarios/cambiarcontraseña")
    public String vistaOlvidona(){
        return "usuarios/cambiarcontraseña";
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

            //LLamo al método del servicio para guardar los datos
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

    //Controlador de Login
    @GetMapping("/usuarios/iniciarsesion")
    public String vistaLogin(){
        return "usuarios/iniciarsesion";
    }
    @PostMapping("/usuarios/iniciarsesion")
    public String validarPasswordPst(@ModelAttribute(name = "loginForm" ) LoginDto loginDto) {
        String emilio = loginDto.getEmilio();
        String clave = loginDto.getClave();
        //¿es correcta la password?
        if (service.getRepo().validarClave(emilio, clave) > 0)
        {
            return "index";
        }else {
            return "usuarios/iniciarsesion";
        }
    }

}
