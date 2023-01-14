package es.geeko.dto;

import es.geeko.model.Comentario;
import es.geeko.model.Reporte;
import es.geeko.model.Tematica;
import es.geeko.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;
@Getter
@Setter
public class ProductoDto {

    private Long id;
    private String titulo;
    private String imagen;
    private String descripcion;
    private double precio;
    private int puntuacion;
    private int videojuego;
    private int libro;
    private int pelicula;
    private int reportado;
    private int activo;
    private Date fechaSubida;
    private List<Comentario> comentario;
    private Usuario usuario;
    private List<Tematica> tematicas;
    private List<Reporte> productosReportados;

    public ProductoDto() {
    }

    public ProductoDto(Long id, String titulo, String imagen, String descripcion, double precio, int puntuacion, int videojuego, int libro, int pelicula, int reportado, int activo, Date fechaSubida, List<Comentario> comentario, Usuario usuario, List<Tematica> tematicas, List<Reporte> productosReportados) {
        this.id = id;
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.puntuacion = puntuacion;
        this.videojuego = videojuego;
        this.libro = libro;
        this.pelicula = pelicula;
        this.reportado = reportado;
        this.activo = activo;
        this.fechaSubida = fechaSubida;
        this.comentario = comentario;
        this.usuario = usuario;
        this.tematicas = tematicas;
        this.productosReportados = productosReportados;
    }


}
