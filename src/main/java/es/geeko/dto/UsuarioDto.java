package es.geeko.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
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

    public UsuarioDto() {
    }

    public UsuarioDto(long id, String nombre, String apellidos, String usuario, String emilio, String clave, String avatar, String direccion1, String direccion2, String cp, String poblacion, String provincia, String tlf, int verificacion2pasos, Date fecha_alta, double valoracion_media, int admin, int activo, String biografia, int reportado) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.emilio = emilio;
        this.clave = clave;
        this.avatar = avatar;
        this.direccion1 = direccion1;
        this.direccion2 = direccion2;
        this.cp = cp;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.tlf = tlf;
        this.verificacion2pasos = verificacion2pasos;
        this.fecha_alta = fecha_alta;
        this.valoracion_media = valoracion_media;
        this.admin = admin;
        this.activo = activo;
        this.biografia = biografia;
        this.reportado = reportado;
    }


}
