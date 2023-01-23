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
public class ComentarioDto {

    private long id;
    private String titulo;
    private String texto;
    private String imagen;
    private Date fecha;
    private int likes;
    private int reportado;
    private int activo;

}
