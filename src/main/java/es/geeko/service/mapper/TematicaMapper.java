package es.geeko.service.mapper;

import es.geeko.dto.TematicaDto;
import es.geeko.model.Tematica;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TematicaMapper extends AbstractServiceMapper<Tematica, TematicaDto> {

    @Override
    public TematicaDto toDto(Tematica entidad){

        //Convertir entidad a dto
        final TematicaDto dto = new TematicaDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,dto);
        return dto;
    }

    @Override
    public Tematica toEntity(TematicaDto dto){

        //Convertir de dto a entidad
        final Tematica entidad = new Tematica();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto, entidad);
        return entidad;
    }

    public TematicaMapper() {
    }
}
