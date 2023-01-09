package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comentarios")
public class Comentario {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="titulo", length = 60)
    private String titulo;

    @Column(name="texto")
    private String texto;

    @Column(name="imagen", length = 200)
    private String imagen;

    @Column(name="fecha")
    @NotNull
    private Date fecha;

    @Column(name="likes")
    private int likes;

    @Column(name="reportado", length = 1)
    private int reportado;

    @Column(name="activo", length = 1)
    @NotNull
    private int activo;

    @Column(name="idPropietario")
    @NotNull
    private int idPropietario;

    @Column(name="idProducto")
    private int idProducto;

    /*
    @ManyToOne
    @JoinColumn(name = "id")
    Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id")
    Producto producto;

    @ManyToOne
    @JoinColumn(name = "id")
    Usuario usuarios;

    @ManyToMany
    @JoinTable(name = "comentarios_reportados",
            joinColumns = @JoinColumn(name = "idComentarioReportado"),
            inverseJoinColumns = @JoinColumn(name = "idReporte"))
    private List<Reporte> reportesComentarios = new ArrayList<>();
*/
    public Comentario() {
    }

    public Comentario(int id, String titulo, String texto, Date fecha, int activo, int idPropietario) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.fecha = fecha;
        this.activo = activo;
        this.idPropietario = idPropietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getReportado() {
        return reportado;
    }

    public void setReportado(int reportado) {
        this.reportado = reportado;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /*
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Reporte> getReportesComentarios() {
        return reportesComentarios;
    }

    public void setReportesComentarios(List<Reporte> reportesComentarios) {
        this.reportesComentarios = reportesComentarios;
    }
*/
}
