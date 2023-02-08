package es.geeko.dto;

import es.geeko.model.Producto;
import es.geeko.model.Tematica;
import es.geeko.model.Usuario;
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
    private List<Usuario> usuarios;
    private List<Producto> productos;
    private Tematica tematica;
    private List<Tematica> tematicas;

}
