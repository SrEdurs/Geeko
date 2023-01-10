package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
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

    @Column(name="título", length = 60)
    @NotNull
    private String titulo;

    @Column(name="imagen", length = 200)
    private String imagen;

    @Column(name="descripción")
    @NotNull
    private String descripcion;

    @Column(name="precio")
    private double precio;

    @Column(name="puntuación")
    private int puntuacion;

    @Column(name="videojuego")
    private int videojuego;

    @Column(name="libro")
    private int libro;

    @Column(name="película")
    private int pelicula;

    @Column(name="idUsuarioPropietario")
    @NotNull
    private int idUsuarioPropietario;

    @Column(name="reportado", length = 1)
    private int reportado;

    @Column(name="activo", length = 1)
    @NotNull
    private int activo;

    @Column(name="fechaSubida")
    private Date fechaSubida;

    /*
    @OneToMany(mappedBy = "idProducto")

    List<Producto> producto;
*/
    @ManyToMany(mappedBy = "productos")
    private List<Tematica> tematicas ;

    /*
    @ManyToMany
    @JoinTable(name = "productos_reportados",
            joinColumns = @JoinColumn(name = "idProductoReportado"),
            inverseJoinColumns = @JoinColumn(name = "idReporte"))
    private List<Reporte> reportesProductos = new ArrayList<>();
*/
    public Producto() {
    }

    public Producto(Long id, String titulo, String imagen, String descripcion, int idUsuarioPropietario) {
        this.id = id;
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.idUsuarioPropietario = idUsuarioPropietario;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", título='" + titulo + '\'' +
                ", imagen='" + imagen + '\'' +
                ", descripción='" + descripcion + '\'' +
                ", precio='" + precio + '\'' +
                ", puntuación='" + puntuacion + '\'' +
                ", videojuego='" + videojuego + '\'' +
                ", libro='" + libro + '\'' +
                ", película='" + pelicula + '\'' +
                ", idUsuarioPropietario='" + idUsuarioPropietario + '\'' +
                ", reportado='" + reportado + '\'' +
                ", activo='" + activo + '\'' +
                ", fechaSubida='" + fechaSubida + '\'' +
                '}';
    }
}