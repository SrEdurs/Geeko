package es.geeko.service.mapper;

import es.geeko.dto.TransaccionDto;
import es.geeko.model.Transaccion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TransaccionMapper extends AbstractServiceMapper<Transaccion, TransaccionDto> {

    @Override
    public TransaccionDto toDto(Transaccion entidad){

        //Convertir entidad a dto
        final TransaccionDto dto = new TransaccionDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,dto);
        return dto;
    }

    @Override
    public Transaccion toEntity(TransaccionDto dto){

        //Convertir de dto a entidad
        final Transaccion entidad = new Transaccion();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto, entidad);
        return entidad;
    }

    public TransaccionMapper() {
    }
}
