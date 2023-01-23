package es.geeko.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

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
    private int reportado;
    private int activo;
    private Date fechaSubida;

}
