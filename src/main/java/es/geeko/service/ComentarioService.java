package es.geeko.service;

import es.geeko.dto.ComentarioDto;
import es.geeko.model.Comentario;
import es.geeko.repository.ComentarioRepository;
import es.geeko.service.mapper.ComentarioMapper;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;

    public ComentarioService(ComentarioRepository comentarioRepository, ComentarioMapper comentarioMapper) {
        this.comentarioRepository = comentarioRepository;
        this.comentarioMapper = comentarioMapper;
    }

    public ComentarioDto save(ComentarioDto comentarioDto){
        //Traduzco del dto con datos de entrada a la entidad
        final Comentario entidad = comentarioMapper.toEntity(comentarioDto);

        //Guardo en la base de datos
        Comentario entidadComentarioGuardada = comentarioRepository.save(entidad);

        //Traducir la entidad a DTO para devolver el DTO
        return comentarioMapper.toDto(entidadComentarioGuardada);
    }
}
