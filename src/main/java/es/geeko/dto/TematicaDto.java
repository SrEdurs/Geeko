package es.geeko.dto;

import es.geeko.model.Producto;
import es.geeko.model.Tematica;
import es.geeko.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TematicaDto {

    private Long id;
    private String nombre;
    private List<Usuario> usuarios;
    private List<Producto> productos;
    private Tematica tematica;
    private List<Tematica> tematicas;

    public TematicaDto() {
    }

    public TematicaDto(Long id, String nombre, List<Usuario> usuarios, List<Producto> productos, Tematica tematica, List<Tematica> tematicas) {
        this.id = id;
        this.nombre = nombre;
        this.usuarios = usuarios;
        this.productos = productos;
        this.tematica = tematica;
        this.tematicas = tematicas;
    }
}
