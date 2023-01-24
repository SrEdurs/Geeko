package es.geeko.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TematicaDto {

    private Long id;
    private String nombre;
    private List<UsuarioDto> usuarios;
    private List<ProductoDto> productos;
    private TematicaDto tematica;
    private List<TematicaDto> tematicas;

}
