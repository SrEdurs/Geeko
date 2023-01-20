package es.geeko.service;

import es.geeko.dto.ComentarioDto;
import es.geeko.model.Comentario;
import es.geeko.repository.ComentarioRepository;
import es.geeko.service.mapper.ComentarioMapper;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService extends AbstractBusinessService<Comentario, Integer, ComentarioDto, ComentarioRepository, ComentarioMapper> {

    public ComentarioService(ComentarioRepository comentarioRepository, ComentarioMapper mapper) {
        super(comentarioRepository, mapper);
    }
}
