package es.geeko.dto;

import es.geeko.model.Producto;
import es.geeko.model.Reporte;
import es.geeko.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class ComentarioDto {

    private long id;
    private String titulo;
    private String texto;
    private String imagen;
    private Date fecha;
    private int likes;
    private int reportado;
    private int activo;
    private Usuario usuario;
    private Producto producto;
    private List<Reporte> comentariosReportados;

    public ComentarioDto() {
    }

    public ComentarioDto(long id, String titulo, String texto, String imagen, Date fecha, int likes, int reportado, int activo, Usuario usuario, Producto producto, List<Reporte> comentariosReportados) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.imagen = imagen;
        this.fecha = fecha;
        this.likes = likes;
        this.reportado = reportado;
        this.activo = activo;
        this.usuario = usuario;
        this.producto = producto;
        this.comentariosReportados = comentariosReportados;
    }
}
