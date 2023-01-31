package es.geeko.service;

import es.geeko.dto.UsuarioDto;
import es.geeko.dto.UsuarioDtoPsw;
import es.geeko.model.Usuario;
import es.geeko.repository.UsuarioRepository;
import es.geeko.service.mapper.UsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class UsuarioService extends AbstractBusinessService<Usuario, Integer, UsuarioDto, UsuarioRepository, UsuarioMapper> {

    public UsuarioService(UsuarioRepository repo, UsuarioMapper mapper) {
        super(repo, mapper);
    }

    public UsuarioDto guardar(UsuarioDto usuarioDto, String password){

        System.out.println("usuarioDto:" +usuarioDto.getEmilio() );

        //Traduzco del dto con datos de entrada a la entidad
        final Usuario entidad = getMapper().toEntity(usuarioDto);
        entidad.setClave(password);

        System.out.println("Entidad:" +entidad.getClave() );
        //Guardo el la base de datos
        Usuario entidadGuardada =  getRepo().save(entidad);
        //Traducir la entidad a DTO para devolver el DTO
        return getMapper().toDto(entidadGuardada);
    }

    public UsuarioDto guardar(UsuarioDtoPsw usuarioDtoPsw){
        System.out.println("usuarioDto:" +usuarioDtoPsw.getEmilio() );

        System.out.println("clave:" +usuarioDtoPsw.getClave() );
        //Traduzco del dto con datos de entrada a la entidad
        final Usuario entidad = getMapper().toEntityPsw(usuarioDtoPsw);
        System.out.println("clave:" +usuarioDtoPsw.getClave() );
        //Guardo el la base de datos
        Usuario entidadGuardada =  getRepo().save(entidad);
        System.out.println("clave:" +usuarioDtoPsw.getClave() );
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