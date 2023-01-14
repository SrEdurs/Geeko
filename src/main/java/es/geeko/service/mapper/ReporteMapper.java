package es.geeko.service.mapper;

import es.geeko.dto.ReporteDto;
import es.geeko.model.Reporte;

public class ReporteMapper {

    public ReporteDto toDto(Reporte reporte){

        //Convertir entidad a dto
        final ReporteDto dto = new ReporteDto();
        dto.setId(reporte.getId());
        dto.setMotivo(reporte.getMotivo());
        dto.setFecha(reporte.getFecha());
        dto.setUsuario(reporte.getUsuario());
        dto.setUsuarios(reporte.getUsuarios());
        dto.setProductos(reporte.getProductos());
        dto.setComentarios(reporte.getComentarios());
        return dto;
    }

    public Reporte toEntity(ReporteDto reporteDto){

        //Convertir de dto a entidad
        final Reporte entidad = new Reporte();
        entidad.setId(reporteDto.getId());
        entidad.setMotivo(reporteDto.getMotivo());
        entidad.setFecha(reporteDto.getFecha());
        entidad.setUsuario(reporteDto.getUsuario());
        entidad.setUsuarios(reporteDto.getUsuarios());
        entidad.setProductos(reporteDto.getProductos());
        entidad.setComentarios(reporteDto.getComentarios());
        return entidad;
    }

    public ReporteMapper() {
    }
}
