package es.geeko.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reportes")
public class Reporte {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="motivo", length = 300)
    private String motivo;

    @Column(name="fecha", nullable = false)
    private Date fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());

    @ManyToOne
    @JoinColumn(name = "idUsuarioReporta")
    private Usuario usuario;

    @ManyToMany(mappedBy = "usuariosReportados")
    private List<Usuario> usuarios;

    @ManyToMany(mappedBy = "productosReportados")
    private List<Producto> productos;

    @ManyToMany(mappedBy = "comentariosReportados", cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();


}
