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

    public Producto(Long id, String titulo, String imagen, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id;
    }
}