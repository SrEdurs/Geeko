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
    private long id;

    @Column(name="titulo", length = 60, nullable = false)
    private String titulo;

    @Column(name="imagen", length = 200)
    private String imagen = "/imagenes/noimage.jpg";

    @Column(name="descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name="precio")
    private double precio = 0;

    @Column(name="videojuego")
    private Integer videojuego;

    @Column(name="libro")
    private Integer libro;

    @Column(name="serie")
    private Integer serie;

    @Column(name="pelicula")
    private Integer pelicula;

    @Column(name="reportado", length = 1)
    private int reportado;

    @Column(name="activo", length = 1, nullable = false)
    private Integer activo;

    @Column(name="geeko", length = 1, nullable = false)
    private Integer geeko = 0;

    @Column(name="fechaSubida")
    private Date fechaSubida;

    @OneToMany(mappedBy = "producto")
    private List<Comentario> comentario;

    @OneToMany(mappedBy = "productoPuntuado")
    private List<Puntuacion> puntuacionProducto;

    @ManyToOne
    @JoinColumn(name = "idUsuarioPropietario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name= "tematica")
    private Tematica tematica;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name="Productos_Reportados",
            joinColumns = @JoinColumn(name="idProductoReportado"),
            inverseJoinColumns = @JoinColumn(name="idReporte")
    )
    private List<Reporte> productosReportados;


}