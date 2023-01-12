package es.geeko.service.mapper;

import es.geeko.dto.UsuarioDto;
import es.geeko.model.Usuario;

public class UsuarioMapper {
    //Convertir de entidad a dto
   /* public UsuarioDto toDto(Usuario usuario){
        final UsuarioDto dto = new UsuarioDto();
        dto.setId(usuario.getId());
        dto.setEmilio(usuario.getEmilio());
        dto.setUsuario(usuario.getUsuario());
        //No se envia la password para el detalle de usuarios
        return dto;
    }
    //Convertir de dto a entidad
    public Usuario toEntity(UsuarioDto usuarioDto){
        final Usuario entidad = new Usuario();
        entidad.setId(usuarioDto.getId());
        entidad.setUsuario(usuarioDto.getUsuario());
        //En caso de nuevo usuario la password se informa
        entidad.setClave(usuarioDto.getClave());
        entidad.setEmilio(usuarioDto.getEmilio());
        return entidad;
    }

    public UsuarioMapper() {
    }*/
}