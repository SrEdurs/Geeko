package es.geeko.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TematicaDto {

    private Long id;
    private String nombre;

    public TematicaDto() {
    }

    public TematicaDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
