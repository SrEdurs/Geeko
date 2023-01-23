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
public class UsuarioDto {

    private long id;
    private String nombre;
    private String apellidos;
    private String usuario;
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
    private Date fecha_alta;
    private double valoracion_media;
    private int admin;
    private int activo;
    private String biografia;
    private int reportado;

}
