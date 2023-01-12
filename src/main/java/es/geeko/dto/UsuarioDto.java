package es.geeko.dto;

import java.sql.Date;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getVerificacion2pasos() {
        return verificacion2pasos;
    }

    public void setVerificacion2pasos(int verificacion2pasos) {
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

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public int getReportado() {
        return reportado;
    }

    public void setReportado(int reportado) {
        this.reportado = reportado;
    }
}
