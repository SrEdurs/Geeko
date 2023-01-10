package es.geeko.model;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import java.sql.Date;
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

    @ManyToOne
    @JoinColumn(name = "idUsuarioReporta")
    private Usuario usuario;

    @ManyToMany(mappedBy = "usuariosReportados")
    private List<Usuario> usuarios;

    @ManyToMany(mappedBy = "productosReportados")
    private List<Producto> productos;

    @ManyToMany(mappedBy = "comentariosReportados")
    private List<Comentario> comentarios;


    public Reporte() {
    }

    public Reporte(int id, String motivo, Date fecha, int idUsuarioReporta) {
        this.id = id;
        this.motivo = motivo;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "id=" + id +
                '}';
    }
}
