package es.geeko.dto;

import es.geeko.model.Producto;
import es.geeko.model.Reporte;
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
public class ComentarioDto {

    private long id;
    private String titulo;
    private String texto;
    private Date fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    private int likes;
    private int reportado = 0;
    private int activo = 1;
    private Usuario usuario;
    private Producto producto;
    private List<Reporte> comentariosReportados;
    private List<ComentarioDto> respuestas;
    private ComentarioDto comentarioPadre;

}
