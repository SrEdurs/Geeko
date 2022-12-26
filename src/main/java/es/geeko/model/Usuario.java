package es.geeko.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
    private boolean verificacion2pasos;
    private Date fecha_alta;
    private double valoracion_media;
    private boolean admin;
    private boolean activo;
    private int idSiguiendo;
    private String biografia;
    private boolean reportado;

    public Usuario() {
    }

    public Usuario(int id, String usuario, String emilio, String clave) {
        this.id = id;
        this.usuario = usuario;
        this.emilio = emilio;
        this.clave = clave;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmilio() {
        return emilio;
    }

    public void setEmilio(String emilio) {
        this.emilio = emilio;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDireccion1() {
        return direccion1;
    }

    public void setDireccion1(String direccion1) {
        this.direccion1 = direccion1;
    }

    public String getDireccion2() {
        return direccion2;
    }

    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public boolean isVerificacion2pasos() {
        return verificacion2pasos;
    }

    public void setVerificacion2pasos(boolean verificacion2pasos) {
        this.verificacion2pasos = verificacion2pasos;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public double getValoracion_media() {
        return valoracion_media;
    }

    public void setValoracion_media(double valoracion_media) {
        this.valoracion_media = valoracion_media;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getIdSiguiendo() {
        return idSiguiendo;
    }

    public void setIdSiguiendo(int idSiguiendo) {
        this.idSiguiendo = idSiguiendo;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public boolean isReportado() {
        return reportado;
    }

    public void setReportado(boolean reportado) {
        this.reportado = reportado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", usuario='" + usuario + '\'' +
                ", emilio='" + emilio + '\'' +
                ", clave='" + clave + '\'' +
                ", avatar='" + avatar + '\'' +
                ", direccion1='" + direccion1 + '\'' +
                ", direccion2='" + direccion2 + '\'' +
                ", cp='" + cp + '\'' +
                ", poblacion='" + poblacion + '\'' +
                ", provincia='" + provincia + '\'' +
                ", tlf='" + tlf + '\'' +
                ", verificacion2pasos=" + verificacion2pasos +
                ", fecha_alta=" + fecha_alta +
                ", valoracion_media=" + valoracion_media +
                ", admin=" + admin +
                ", activo=" + activo +
                ", idSiguiendo=" + idSiguiendo +
                ", biografia='" + biografia + '\'' +
                ", reportado=" + reportado +
                '}';
    }
}


