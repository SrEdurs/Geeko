package es.geeko.dto;

import es.geeko.model.*;
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
public class UsuarioDto {

    private long id;
    private String nombre;
    private String apellidos;
    private String nick;
    private String emilio;
    private String clave;
    private String avatar;
    private String direccion1;
    private String direccion2;
    private String cp;
    private String poblacion;
    private String provincia;
    private String tlf;
    private int verificacion2pasos;
    private Date fecha_alta = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    private double valoracion_media;
    private int activo = 1;
    private String biografia;
    private int reportado;
    private List<ComentarioDto> comentarios;
    private List<Producto> productos;
    private List<Tematica> tematicas;
    private List<Transaccion> transacciones;
    private List<Reporte> usuariosReportados;
    private List<Usuario> seguimientos;
    private List<Usuario> seguidos;
    private List<String> roles;
    private List<Like> likes;

}
