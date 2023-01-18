package es.geeko.service;

import es.geeko.dto.TematicaDto;
import es.geeko.model.Tematica;
import es.geeko.repository.TematicaRepository;
import es.geeko.service.mapper.TematicaMapper;
import org.springframework.stereotype.Service;

@Service
public class TematicaService extends AbstractBusinessService<Tematica, Integer, TematicaDto, TematicaRepository, TematicaMapper> {

    public TematicaService(TematicaRepository tematicaRepository, TematicaMapper mapper) {
        super(tematicaRepository, mapper);
    }
}
