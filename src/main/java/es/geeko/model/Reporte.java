package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
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

    @ManyToMany(mappedBy = "reportesComentarios")
    private List<Comentario> comentarios = new ArrayList<>();

}
