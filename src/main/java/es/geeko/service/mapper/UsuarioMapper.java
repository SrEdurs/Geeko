package es.geeko.service.mapper;

import es.geeko.dto.UsuarioDto;
import es.geeko.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMapper extends AbstractServiceMapper<Usuario,UsuarioDto> {

    //Convertir de entidad a dto
    @Override
   public UsuarioDto toDto(Usuario usuario){
        final UsuarioDto dto = new UsuarioDto();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellidos(usuario.getApellidos());
        dto.setUsuario(usuario.getUsuario());
        dto.setEmilio(usuario.getEmilio());
        dto.setClave(usuario.getClave());
        dto.setAvatar(usuario.getAvatar());
        dto.setDireccion1(usuario.getDireccion1());
        dto.setDireccion2(usuario.getDireccion2());
        dto.setCp(usuario.getCp());
        dto.setPoblacion(usuario.getPoblacion());
        dto.setProvincia(usuario.getProvincia());
        dto.setTlf(usuario.getTlf());
        dto.setVerificacion2pasos(usuario.getVerificacion2pasos());
        dto.setFecha_alta(usuario.getFecha_alta());
        dto.setValoracion_media(usuario.getValoracion_media());
        dto.setAdmin(usuario.getAdmin());
        dto.setActivo(usuario.getActivo());
        dto.setBiografia(usuario.getBiografia());
        dto.setReportado(usuario.getReportado());
        return dto;
    }

    //Convertir de dto a entidad
    @Override
    public Usuario toEntity(UsuarioDto usuarioDto){
        final Usuario entidad = new Usuario();
        entidad.setId(usuarioDto.getId());
        entidad.setNombre(usuarioDto.getNombre());
        entidad.setApellidos(usuarioDto.getApellidos());
        entidad.setUsuario(usuarioDto.getUsuario());
        entidad.setEmilio(usuarioDto.getEmilio());
        entidad.setClave(usuarioDto.getClave());
        entidad.setAvatar(usuarioDto.getAvatar());
        entidad.setDireccion1(usuarioDto.getDireccion1());
        entidad.setDireccion2(usuarioDto.getDireccion2());
        entidad.setCp(usuarioDto.getCp());
        entidad.setPoblacion(usuarioDto.getPoblacion());
        entidad.setProvincia(usuarioDto.getProvincia());
        entidad.setTlf(usuarioDto.getTlf());
        entidad.setVerificacion2pasos(usuarioDto.getVerificacion2pasos());
        entidad.setValoracion_media(usuarioDto.getValoracion_media());
        entidad.setAdmin(usuarioDto.getAdmin());
        entidad.setActivo(usuarioDto.getActivo());
        entidad.setBiografia(usuarioDto.getBiografia());
        entidad.setReportado(usuarioDto.getReportado());
        return entidad;
    }

    public UsuarioMapper() {
    }
}