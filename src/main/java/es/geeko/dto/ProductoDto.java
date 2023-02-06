package es.geeko.dto;

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

    private Long id;
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
    private List<ComentarioDto> comentario;
    private UsuarioDto usuario;
    private List<TematicaDto> tematicas;
    private List<ReporteDto> productosReportados;

}
