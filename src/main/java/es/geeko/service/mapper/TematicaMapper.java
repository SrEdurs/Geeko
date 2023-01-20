package es.geeko.service.mapper;

import es.geeko.dto.TematicaDto;
import es.geeko.model.Tematica;
import org.springframework.stereotype.Service;

@Service
public class TematicaMapper extends AbstractServiceMapper<Tematica, TematicaDto> {

    @Override
    public TematicaDto toDto(Tematica tematica){

        //Convertir entidad a dto
        final TematicaDto dto = new TematicaDto();
        dto.setId(tematica.getId());
        dto.setNombre(tematica.getNombre());
        return dto;
    }

    @Override
    public Tematica toEntity(TematicaDto tematicaDto){

        //Convertir de dto a entidad
        final Tematica entidad = new Tematica();
        entidad.setId(tematicaDto.getId());
        entidad.setNombre(tematicaDto.getNombre());
        return entidad;
    }

    public TematicaMapper() {
    }
}
