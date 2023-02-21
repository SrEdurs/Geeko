package es.geeko.service.mapper;

import es.geeko.dto.MensajeDto;
import es.geeko.model.Mensaje;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MensajeMapper extends AbstractServiceMapper<Mensaje, MensajeDto> {

    @Override
    public MensajeDto toDto(Mensaje entidad){

        //Convertir entidad a dto
        final MensajeDto dto = new MensajeDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,dto);
        return dto;
    }

    @Override
    public Mensaje toEntity(MensajeDto dto){

        //Convertir de dto a entidad
        final Mensaje entidad = new Mensaje();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto, entidad);
        return entidad;
    }

    public MensajeMapper() {
    }
}
