package es.geeko.dto;

import es.geeko.model.Comentario;
import es.geeko.model.Producto;
import es.geeko.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class ReporteDto {

    private long id;
    private String motivo;
    private Date fecha;
    private Usuario usuario;
    private List<Usuario> usuarios;
    private List<Producto> productos;
    private List<Comentario> comentarios;

    public ReporteDto() {
    }

    public ReporteDto(long id, String motivo, Date fecha, Usuario usuario, List<Usuario> usuarios, List<Producto> productos, List<Comentario> comentarios) {
        this.id = id;
        this.motivo = motivo;
        this.fecha = fecha;
        this.usuario = usuario;
        this.usuarios = usuarios;
        this.productos = productos;
        this.comentarios = comentarios;
    }
}
