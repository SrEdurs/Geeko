package es.geeko.service.mapper;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractServiceMapper<E,DTO> {
    //Convertir de entidad a dto
    public abstract DTO toDto(E e);
    //Convertir de dto a entidad
    public abstract E toEntity(DTO dto) throws Exception;
    //Conversion de listas de entidades a DTOs
    public List<DTO>  toDto(List<E> e){
        return e.stream().map(this::toDto).collect(Collectors.toList());
    }
}
