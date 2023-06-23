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
@Table(name = "comentarios")
public class Comentario {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="texto")
    private String texto;

    @Column(name="fecha")
    private Date fecha;

    @Column(name="likes")
    private int likes;

    @Column(name="reportado", length = 1)
    private int reportado;

    @Column(name="activo", length = 1, nullable = false)
    private int activo;

    @OneToMany(mappedBy = "comentarioLike")
    private List<Like> likesComen;

    @ManyToOne
    @JoinColumn(name = "idPropietario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name="Comentarios_Reportados",
            joinColumns = @JoinColumn(name="idComentarioReportado"),
            inverseJoinColumns = @JoinColumn(name="idReporte")
    )
    private List<Reporte> comentariosReportados;

    //Relación many to one recursiva para las respuestas de un comentario
    @ManyToOne
    @JoinColumn(name = "idComentarioPadre")
    private Comentario comentarioPadre;

    @OneToMany(mappedBy = "comentarioPadre")
    private List<Comentario> comentariosHijos;

}
