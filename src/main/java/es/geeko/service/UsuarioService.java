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