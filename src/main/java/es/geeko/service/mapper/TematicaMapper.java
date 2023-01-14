package es.geeko.service.mapper;

import es.geeko.dto.TematicaDto;
import es.geeko.model.Tematica;

public class TematicaMapper {

    public TematicaDto toDto(Tematica tematica){

        //Convertir entidad a dto
        final TematicaDto dto = new TematicaDto();
        dto.setId(tematica.getId());
        dto.setNombre(tematica.getNombre());
        dto.setUsuarios(tematica.getUsuarios());
        dto.setProductos(tematica.getProductos());
        dto.setTematica(tematica.getTematica());
        dto.setTematicas(tematica.getTematicas());
        return dto;
    }

    public Tematica toEntity(TematicaDto tematicaDto){

        //Convertir de dto a entidad
        final Tematica entidad = new Tematica();
        entidad.setId(tematicaDto.getId());
        entidad.setNombre(tematicaDto.getNombre());
        entidad.setUsuarios(tematicaDto.getUsuarios());
        entidad.setProductos(tematicaDto.getProductos());
        entidad.setTematica(tematicaDto.getTematica());
        entidad.setTematicas(tematicaDto.getTematicas());
        return entidad;
    }

    public TematicaMapper() {
    }
}
