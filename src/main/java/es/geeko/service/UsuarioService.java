package es.geeko.service;

import es.geeko.dto.UsuarioDto;
import es.geeko.model.Usuario;
import es.geeko.repository.UsuarioRepository;
import es.geeko.service.mapper.UsuarioMapper;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

   //Acceso a los datos de la bbdd
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = new UsuarioMapper();
    }

    //Método para guardar usuarios
    //La entrada es un DTO (que viene de la pantalla)
    //La respuesta es un DTO del registro almacenado
    public UsuarioDto save(UsuarioDto usuarioDto) {

        //Traduzco del dto con datos de entrada a la entidad
        final Usuario entidad = usuarioMapper.toEntity(usuarioDto);

        //Guardo en la base de datos
        Usuario entidadUsuarioGuardada = usuarioRepository.save(entidad);

        //Traducir la entidad a DTO para devolver el DTO
        return usuarioMapper.toDto(entidadUsuarioGuardada);
    }
}