package es.geeko.service.mapper;

import es.geeko.dto.ReporteDto;
import es.geeko.model.Reporte;
import org.springframework.stereotype.Service;

@Service
public class ReporteMapper extends AbstractServiceMapper<Reporte, ReporteDto> {

    @Override
    public ReporteDto toDto(Reporte reporte){

        //Convertir entidad a dto
        final ReporteDto dto = new ReporteDto();
        dto.setId(reporte.getId());
        dto.setMotivo(reporte.getMotivo());
        dto.setFecha(reporte.getFecha());
        return dto;
    }

    @Override
    public Reporte toEntity(ReporteDto reporteDto){

        //Convertir de dto a entidad
        final Reporte entidad = new Reporte();
        entidad.setId(reporteDto.getId());
        entidad.setMotivo(reporteDto.getMotivo());
        entidad.setFecha(reporteDto.getFecha());
        return entidad;
    }

    public ReporteMapper() {
    }
}
