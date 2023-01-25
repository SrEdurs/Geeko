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
public class ReporteDto {

    private long id;
    private String motivo;
    private Date fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    private UsuarioDto usuario;
    private List<UsuarioDto> usuarios;
    private List<ProductoDto> productos;
    private List<ComentarioDto> comentarios;


}
