package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reportes")
public class Reporte {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="motivo", length = 300)
    private String motivo;

    @Column(name="fecha")
    @NotNull
    private Date fecha;

    @Column(name="idUsuarioReporta")
    @NotNull
    private int idUsuarioReporta;

    @ManyToMany(mappedBy = "reportes")
    private List<Usuario> usuarios;
/*
    @ManyToOne
    @JoinColumn(name = "id")
    Usuario usuario;

    @ManyToMany(mappedBy = "reportesComentarios")
    private List<Comentario> comentarios = new ArrayList<>();

    @ManyToMany(mappedBy = "reportesProductos")
    private List<Producto> productos = new ArrayList<>();
*/

    public Reporte() {
    }

    public Reporte(int id, String motivo, Date fecha, int idUsuarioReporta) {
        this.id = id;
        this.motivo = motivo;
        this.fecha = fecha;
        this.idUsuarioReporta = idUsuarioReporta;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "id=" + id +
                '}';
    }
}
