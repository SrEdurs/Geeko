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
@Table(name = "reportes")
public class Reporte {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    public Reporte(long id, String motivo, Date fecha, Usuario usuario, List<Usuario> usuarios, List<Producto> productos, List<Comentario> comentarios) {
        this.id = id;
        this.motivo = motivo;
        this.fecha = fecha;
        this.usuario = usuario;
        this.usuarios = usuarios;
        this.productos = productos;
        this.comentarios = comentarios;
    }
}
