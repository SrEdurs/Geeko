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

    @GetMapping("")
    public String vistaIndex(){
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

    @GetMapping("/usuarios/perfil")
    public String vistaperfil(){
        return "/usuarios/perfil";
    }

    @GetMapping("/usuarios/perfilotrousuario")
    public String vistaotroperfil(){
        return "/usuarios/perfilotrousuario";
    }

    @GetMapping("/usuarios/perfiladmin")
    public String vistaperfiladmin(){
        return "/usuarios/perfiladmin";
    }

    @GetMapping("/social/social")
    public String vistasocial(){
        return "/social/social";
    }

    @GetMapping("/usuarios/cambiarcontraseña")
    public String vistaOlvidona(){
        return "usuarios/cambiarcontraseña";
    }

}
