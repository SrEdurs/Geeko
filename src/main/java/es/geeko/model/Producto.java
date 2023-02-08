package es.geeko.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productos")

public class Producto {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="titulo", length = 60, nullable = false)
    private String titulo;

    @Column(name="imagen", length = 200)
    private String imagen = "/imagenes/noimage.jpg";

    @Column(name="descripcion", nullable = false)
    private String descripcion;

    @Column(name="precio")
    private double precio;

    @Column(name="puntuacion")
    private int puntuacion;

    @Column(name="videojuego")
    private int videojuego;

    @Column(name="libro")
    private int libro;

    @Column(name="serie")
    private int serie;

    @Column(name="pelicula")
    private int pelicula;

    @Column(name="reportado", length = 1)
    private int reportado;

    @Column(name="activo", length = 1, nullable = false)
    private int activo;

    @Column(name="fechaSubida")
    private Date fechaSubida;

    @OneToMany(mappedBy = "producto")
    private List<Comentario> comentario;

    @ManyToOne
    @JoinColumn(name = "idUsuarioPropietario")
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name="Tematica_Productos",
            joinColumns = @JoinColumn(name="Productos_id"),
            inverseJoinColumns = @JoinColumn(name="Tematica_id")
    )
    private List<Tematica> tematicas;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name="Productos_Reportados",
            joinColumns = @JoinColumn(name="idProductoReportado"),
            inverseJoinColumns = @JoinColumn(name="idReporte")
    )
    private List<Reporte> productosReportados;

}