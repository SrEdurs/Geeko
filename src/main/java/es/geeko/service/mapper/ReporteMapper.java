package es.geeko.service.mapper;

import es.geeko.dto.ReporteDto;
import es.geeko.model.Reporte;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ReporteMapper extends AbstractServiceMapper<Reporte, ReporteDto> {

    @Override
    public ReporteDto toDto(Reporte entidad){

        //Convertir entidad a dto
        final ReporteDto dto = new ReporteDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,dto);
        return dto;
    }

    @Override
    public Reporte toEntity(ReporteDto dto){

        //Convertir de dto a entidad
        final Reporte entidad = new Reporte();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto, entidad);
        return entidad;
    }

    public ReporteMapper() {
    }
}
