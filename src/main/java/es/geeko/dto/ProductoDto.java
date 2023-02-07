package es.geeko.dto;

import es.geeko.model.Comentario;
import es.geeko.model.Reporte;
import es.geeko.model.Tematica;
import es.geeko.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDto {

    private int id;
    private String titulo;
    private String imagen;
    private String descripcion;
    private double precio;
    private int puntuacion;
    private int videojuego;
    private int libro;
    private int pelicula;
    private int serie;
    private int reportado = 0;
    private int activo = 1;
    private Date fechaSubida = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    private List<Comentario> comentario;
    private Usuario usuario;
    private List<Tematica> tematicas;
    private List<Reporte> productosReportados;

}
