package es.geeko.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class MensajeDto {

    private long id;
    private Date fecha;
    private String texto;
    private String imagen;

    public MensajeDto() {
    }

    public MensajeDto(long id, Date fecha, String texto, String imagen) {
        this.id = id;
        this.fecha = fecha;
        this.texto = texto;
        this.imagen = imagen;
    }
}
