package es.geeko.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

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

    public ComentarioDto() {
    }

    public ComentarioDto(long id, String titulo, String texto, String imagen, Date fecha, int likes, int reportado, int activo) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.imagen = imagen;
        this.fecha = fecha;
        this.likes = likes;
        this.reportado = reportado;
        this.activo = activo;
    }
}
