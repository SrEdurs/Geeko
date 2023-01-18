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
@Table(name = "comentarios")
public class Comentario {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @ManyToOne
    @JoinColumn(name = "idPropietario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @ManyToMany
    @JoinTable(
            name="Comentarios_Reportados",
            joinColumns = @JoinColumn(name="idComentarioReportado"),
            inverseJoinColumns = @JoinColumn(name="idReporte")
    )
    private List<Reporte> comentariosReportados;


    public Comentario() {
    }

    public Comentario(long id, String titulo, String texto, Date fecha, int activo) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.fecha = fecha;
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                '}';
    }
}