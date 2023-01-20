package es.geeko.service.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractServiceMapper<E,DTO> {
    //Convertir de entidad a dto
    public abstract DTO toDto(E e);
    //Convertir de dto a entidad
    public abstract E toEntity(DTO dto);

    //Conversion de listas de dtos a entidades
    public List<E>  toEntity(List<DTO> dtos){
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
    //Conversion de listas de entidades a DTOs
    public List<DTO>  toDto(List<E> e){
        return e.stream().map(this::toDto).collect(Collectors.toList());
    }

    //Gestionamos set de datos
    public Set<E> toEntity(Set<DTO> dtos){
        return dtos.stream().map(this::toEntity).collect(Collectors.toSet());
    }
    //Conversion de listas de entidades a DTOs
    public Set<DTO>  toDto(Set<E> e){
        return e.stream().map(this::toDto).collect(Collectors.toSet());
    }
}
