package es.geeko.service;

import es.geeko.dto.UsuarioDto;
import es.geeko.model.Usuario;
import es.geeko.repository.UsuarioRepository;
import es.geeko.service.mapper.UsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class UsuarioService extends AbstractBusinessService<Usuario, Integer, UsuarioDto, UsuarioRepository, UsuarioMapper> {

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper mapper) {
        super(usuarioRepository, mapper);
    }

    public UsuarioDto guardar(UsuarioDto usuarioDto, String password){
        //Traduzco del dto con datos de entrada a la entidad
        final Usuario entidad = getMapper().toEntity(usuarioDto);
        entidad.setClave(password);
        //Guardo el la base de datos
        Usuario entidadGuardada =  getRepo().save(entidad);
        //Traducir la entidad a DTO para devolver el DTO
        return getMapper().toDto(entidadGuardada);
    }

    @Override
    public void guardar(List<UsuarioDto> lUsuarioDto){
        Iterator<UsuarioDto> it = lUsuarioDto.iterator();

        // mientras al iterador queda próximo juego
        while(it.hasNext()){
            //Obtenemos la password de a entidad
            //Datos del usuario
            Usuario usuario = getMapper().toEntity(it.next());
            usuario.setClave(getRepo().getReferenceById((int)usuario.getId()).getClave());
            getRepo().save(usuario);
        }
    }
}