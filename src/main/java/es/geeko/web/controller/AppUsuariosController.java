package es.geeko.web.controller;

import es.geeko.dto.LoginDto;
import es.geeko.dto.TematicaDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.Tematica;
import es.geeko.model.Usuario;
import es.geeko.service.TematicaService;
import es.geeko.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AppUsuariosController extends AbstractController<UsuarioDto> {

    private final  UsuarioService usuarioService;
    private final TematicaService tematicaService;

    public AppUsuariosController(UsuarioService usuarioService, TematicaService tematicaService) {
        this.usuarioService = usuarioService;
        this.tematicaService = tematicaService;
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
        UsuarioDto usuarioGuardado =  this.usuarioService.guardar(usuarioDto);
        System.out.println(usuarioGuardado.getClave());
        Long id = usuarioGuardado.getId();
        return "usuarios/cuestionario";
    }

    @GetMapping("/usuarios/cuestionario")
    public String vistaCuestionario(Model interfazConPantalla){
        final List<Tematica> tematicas = tematicaService.buscarEntidades();
        interfazConPantalla.addAttribute("listaTematicas",tematicas);
        return "/usuarios/cuestionario";
    }

    @PostMapping("/usuarios/cuestionario")
    public String guardarCuestionario(UsuarioDto usuarioDtoEntrada) throws Exception{

        UsuarioDto usuarioGuardado = this.usuarioService.guardar(usuarioDtoEntrada);
        Long id = usuarioGuardado.getId();
        return "perfil";

    }



    //Controlador de Login
    @GetMapping("/usuarios/iniciarsesion")
    public String vistaLogin(){
        return "usuarios/iniciarsesion";
    }

    @PostMapping("/usuarios/iniciarsesion")
    public String validarClave(@ModelAttribute(name = "login" ) LoginDto loginDto) {
        String emilio = loginDto.getEmilio();
        System.out.println("emilio = " + emilio);
        String clave = loginDto.getClave();
        System.out.println("clave = " + clave);
        //¿es correcta la password?
        if (usuarioService.getRepo().validarClave(emilio,clave) > 0)
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
