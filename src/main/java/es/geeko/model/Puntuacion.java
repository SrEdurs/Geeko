package es.geeko.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "puntuaciones")
public class Puntuacion {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="puntuacion")
    private int puntuacion;

    @ManyToOne
    @JoinColumn(name = "idUsuarioPuntua")
    private Usuario usuarioPuntua;

    @ManyToOne
    @JoinColumn(name = "idProductoPuntuado")
    private Producto productoPuntuado;

    @ManyToOne
    @JoinColumn(name = "idUsuarioPuntuado")
    private Usuario usuarioPuntuado;

}
