package es.geeko.service;

import es.geeko.dto.TematicaDto;
import es.geeko.model.Tematica;
import es.geeko.repository.TematicaRepository;
import es.geeko.service.mapper.TematicaMapper;
import org.springframework.stereotype.Service;

@Service
public class TematicaService {

    private final TematicaRepository tematicaRepository;
    private final TematicaMapper tematicaMapper;

    public TematicaService(TematicaRepository tematicaRepository) {
        this.tematicaRepository = tematicaRepository;
        this.tematicaMapper = new TematicaMapper();
    }

    public TematicaDto save(TematicaDto tematicaDto){
        final Tematica entidad = tematicaMapper.toEntity(tematicaDto);
        Tematica entidadTematicaGuardada = tematicaRepository.save(entidad);
        return tematicaMapper.toDto(entidadTematicaGuardada);
    }
}
