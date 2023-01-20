package es.geeko.service.mapper;

import es.geeko.dto.ComentarioDto;
import es.geeko.model.Comentario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ComentarioMapper extends AbstractServiceMapper<Comentario, ComentarioDto> {

    @Override
    public ComentarioDto toDto(Comentario entidad){

        //Convertir de entidad a dto
        final ComentarioDto dto = new ComentarioDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,dto);
        return dto;
    }

    @Override
    public Comentario toEntity(ComentarioDto dto){

        //Convertir de dto a entidad
        final Comentario entidad = new Comentario();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto, entidad);
        return entidad;
    }

    public ComentarioMapper() {
    }
}
