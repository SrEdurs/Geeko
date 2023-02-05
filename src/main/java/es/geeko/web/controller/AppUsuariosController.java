package es.geeko.web.controller;

import es.geeko.dto.LoginDto;
import es.geeko.dto.TematicaDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.dto.UsuarioDtoPsw;
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

//@Controller
public class AppUsuariosController extends AbstractController<UsuarioDto> {

    /*private final  UsuarioService usuarioService;
    private final TematicaService tematicaService;

    public AppUsuariosController(UsuarioService usuarioService, TematicaService tematicaService) {
        this.usuarioService = usuarioService;
        this.tematicaService = tematicaService;
    }
*/
   /* @GetMapping("")
    public String vistaIndex(){
        return "index";
    }*/

    /*@GetMapping("/usuarios/crearcuenta")
    public String vistaRegistro(Model interfazConPantalla){
        //Instancia en memoria del dto a informar en la pantalla
        final UsuarioDtoPsw usuarioDtoPsw = new UsuarioDtoPsw();
        //Obtengo la lista de roles
        //Mediante "addAttribute" comparto con la pantalla
        interfazConPantalla.addAttribute("datosUsuario",usuarioDtoPsw);
        return "usuarios/crearcuenta";
    }
    //El que con los datos de la pantalla guarda la información de tipo PostMapping
    @PostMapping("/usuarios/crearcuenta")
    public String guardarUsuario( UsuarioDtoPsw usuarioDtoPsw) throws Exception {
        //LLamo al método del servicioi para guardar los datos
        UsuarioDto usuarioGuardado =  this.usuarioService.guardar(usuarioDtoPsw);
        System.out.println("clave DTOPSW:" +usuarioDtoPsw.getClave() );
        System.out.println("clave GUARDADO:" +usuarioGuardado.getClave() );
        Long id = usuarioGuardado.getId();
        //return "usuarios/detallesusuario";
        return String.format("redirect:/usuarios/%s", id);
    }

    @GetMapping("/usuarios/{idusr}")
    public String vistaDatosUsuario(@PathVariable("idusr") Integer id, ModelMap interfazConPantalla){
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<UsuarioDto> usuarioDto = this.usuarioService.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (usuarioDto.isPresent()){
            //Como encontré datos, obtengo el objeto de tipo "UsuarioDto"
            //addAttribute y thymeleaf no entienden Optional
            UsuarioDto attr = usuarioDto.get();
            System.out.println("La password es:");
            System.out.println(attr.getClave());
            //Asigno atributos y muestro
            interfazConPantalla.addAttribute("datosUsuario",attr);

            return "usuarios/edit";
        } else{
            //Mostrar página usuario no existe
            return "error";
        }
    }

    //Me falta un postmaping para guardar
    @PostMapping("/usuarios/{idusr}")
    public String guardarEdicionDatosUsuario(@PathVariable("idusr") Integer id, UsuarioDto usuarioDtoEntrada) throws Exception {
        //Con el id tengo que buscar el registro a nivel de entidad
        Optional<UsuarioDto> usuarioDtoControl = this.usuarioService.encuentraPorId(id);
        //¿Debería comprobar si hay datos?
        if (usuarioDtoControl.isPresent()){
            UsuarioDto usuarioDtoGuardar =  new UsuarioDto();
            usuarioDtoGuardar.setId(id);
            usuarioDtoGuardar.setEmilio(usuarioDtoEntrada.getEmilio());
            usuarioDtoGuardar.setUsuario(usuarioDtoEntrada.getUsuario());
            usuarioDtoGuardar.setNombre(usuarioDtoEntrada.getNombre());
            usuarioDtoGuardar.setApellidos(usuarioDtoEntrada.getApellidos());
            usuarioDtoGuardar.setAvatar(usuarioDtoEntrada.getAvatar());
            usuarioDtoGuardar.setDireccion1(usuarioDtoEntrada.getDireccion1());
            usuarioDtoGuardar.setDireccion2(usuarioDtoEntrada.getDireccion2());
            usuarioDtoGuardar.setPoblacion(usuarioDtoEntrada.getPoblacion());
            usuarioDtoGuardar.setProvincia(usuarioDtoEntrada.getProvincia());
            usuarioDtoGuardar.setTlf(usuarioDtoEntrada.getTlf());
            usuarioDtoGuardar.setBiografia(usuarioDtoEntrada.getBiografia());
            usuarioDtoGuardar.setClave(usuarioDtoControl.get().getClave());

        System.out.println("usuarioDtoEntrada = " + usuarioDtoEntrada.getClave());
            //Obtenemos la password del servicio

            this.usuarioService.guardar(usuarioDtoGuardar);

            System.out.println("usuarioDtoEntrada = " + usuarioDtoGuardar.getClave());
            return String.format("redirect:/usuarios/%s", id);
        } else {
            //Mostrar página usuario no existe
            return "index";
        }

    }

*/

    /*//Controlador de Login
    @GetMapping("/usuarios/login")
    public String vistaLogin(){
        return "usuarios/login";
    }

    @PostMapping("/usuarios/login")
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
            return "usuarios/login";
        }
    }
*/
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

    /*@GetMapping("/usuarios/cuestionario")
    public String vistaCuestionario(ModelMap interfazConPantalla){

        final List<Tematica> tematicas = tematicaService.buscarEntidades();
        interfazConPantalla.addAttribute("listaTematicas",tematicas);


        return "usuarios/cuestionario";
    }*/

}
