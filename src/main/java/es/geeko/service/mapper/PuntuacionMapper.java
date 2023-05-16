package es.geeko.service.mapper;

import es.geeko.dto.PuntuacionDto;
import es.geeko.model.Puntuacion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PuntuacionMapper extends AbstractServiceMapper<Puntuacion, PuntuacionDto> {

    //Convertir de entidad a dto
    @Override
    public PuntuacionDto toDto(Puntuacion entidad){
        final PuntuacionDto dto = new PuntuacionDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,dto);
        return dto;
    }

    //Convertir de dto a entidad
    @Override
    public Puntuacion toEntity(PuntuacionDto dto) {
        final Puntuacion entidad = new Puntuacion();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto, entidad);
        return entidad;
    }

    public PuntuacionMapper() {
    }
}
