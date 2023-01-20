package es.geeko.model;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "productos")

public class Producto {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="titulo", length = 60)
    @NotNull
    private String titulo;

    @Column(name="imagen", length = 200)
    private String imagen;

    @Column(name="descripcion")
    @NotNull
    private String descripcion;

    @Column(name="precio")
    private double precio;

    @Column(name="puntuacion")
    private int puntuacion;

    @Column(name="videojuego")
    private int videojuego;

    @Column(name="libro")
    private int libro;

    @Column(name="pelicula")
    private int pelicula;

    @Column(name="reportado", length = 1)
    private int reportado;

    @Column(name="activo", length = 1)
    @NotNull
    private int activo;

    @Column(name="fechaSubida")
    private Date fechaSubida;

    @OneToMany(mappedBy = "producto")
    private List<Comentario> comentario;

    @ManyToOne
    @JoinColumn(name = "idUsuarioPropietario")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name="Tematica_Productos",
            joinColumns = @JoinColumn(name="Productos_id"),
            inverseJoinColumns = @JoinColumn(name="Tematica_id")
    )
    private List<Tematica> tematicas;

    @ManyToMany
    @JoinTable(
            name="Productos_Reportados",
            joinColumns = @JoinColumn(name="idProductoReportado"),
            inverseJoinColumns = @JoinColumn(name="idReporte")
    )
    private List<Reporte> productosReportados;


    public Producto() {
    }

    public Producto(Long id, String titulo, String imagen, String descripcion, double precio, int puntuacion, int videojuego, int libro, int pelicula, int reportado, int activo, Date fechaSubida, List<Comentario> comentario, Usuario usuario, List<Tematica> tematicas, List<Reporte> productosReportados) {
        this.id = id;
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
        this.puntuacion = puntuacion;
        this.videojuego = videojuego;
        this.libro = libro;
        this.pelicula = pelicula;
        this.reportado = reportado;
        this.activo = activo;
        this.fechaSubida = fechaSubida;
        this.comentario = comentario;
        this.usuario = usuario;
        this.tematicas = tematicas;
        this.productosReportados = productosReportados;
    }
}