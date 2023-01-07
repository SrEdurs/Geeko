package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nombre", length = 60)
    private String nombre;

    @Column(name="apellidos", length = 60)
    private String apellidos;

    @Column(name="usuario", length = 30)
    @NotNull
    private String usuario;

    @Column(name="emilio", length = 100)
    @NotNull
    private String emilio;

    @Column(name="clave", length = 255)
    @NotNull
    private String clave;

    @Column(name="avatar", length = 255)
    private String avatar;

    @Column(name="direccion1", length = 50)
    private String direccion1;

    @Column(name="direccion2", length = 70)
    private String direccion2;

    @Column(name="cp", length = 5)
    private String cp;

    @Column(name="poblacion", length = 60)
    private String poblacion;

    @Column(name="provincia", length = 45)
    private String provincia;

    @Column(name="tlf", length = 15)
    private String tlf;

    @Column(name="verificacion2pasos", length = 1)
    private int verificacion2pasos;

    @Column(name="fecha_alta")
    private Date fecha_alta;

    @Column(name="valoracion_media")
    private double valoracion_media;

    @Column(name="admin", length = 1)
    private int admin;

    @Column(name="activo", length = 1)
    private int activo;

    @Column(name="biografia", length = 160)
    private String biografia;

    @Column (name="reportado", length = 1)
    private int reportado;

    @OneToMany(mappedBy = "idRemitente")
    List<Mensaje> mensajes;

    @ManyToOne
    @JoinColumn(name = "idPropietario")
    Producto producto;

    @ManyToMany
    @JoinTable(name = "seguimientos",
            joinColumns = @JoinColumn(name = "idSeguidor"),
            inverseJoinColumns = @JoinColumn(name = "idSeguido"))
    private List<Usuario> seguimientos = new ArrayList<>();

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
                ", biografia='" + biografia + '\'' +
                ", reportado=" + reportado +
                '}';
    }
}


