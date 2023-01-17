package es.geeko.web.controller;

import es.geeko.dto.UsuarioDto;
import es.geeko.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AppMainController {

    private final  UsuarioService service;

    public AppMainController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("")
    public String vistaHome(){
        return "index";
    }

    @GetMapping("/login")
    public String vistaLogin(){
        return "login";
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
    public String guardarUsuario(UsuarioDto usuarioDto){
        //LLamo al método del servicio para guardar los datos
        UsuarioDto usuarioGuardado =  this.service.save(usuarioDto);
        Long id = usuarioGuardado.getId();
        return String.format("redirect:/usuarios/%s", id);
    }
    @GetMapping("/usuarios")
    public String vistaUsuarios( ModelMap interfazConPantalla){
        //Tenemos que leer la lista de usuarios
        //Que elemento me la ofrece?
        //listaUsrTodos
        List<UsuarioDto> lusrdto = this.service.listaUsrTodos();
        interfazConPantalla.addAttribute("listausuarios", lusrdto);
        return "usuarios/listausuarios";
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

            //Asigno atributos y muestro
            interfazConPantalla.addAttribute("datosUsuario",attr);
            return "usuarios/perfil";
        } else{
            //Mostrar página usuario no existe
            return "usuarios/error";
        }
    }




}
