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

    public ReporteDto() {
    }

    public ReporteDto(long id, String motivo, Date fecha) {
        this.id = id;
        this.motivo = motivo;
        this.fecha = fecha;
    }
}
