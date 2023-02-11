package es.geeko.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tematicas")
public class Tematica {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @ManyToMany(mappedBy = "tematicas")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "tematica")
    private List<Producto> productos;

    //Relación One to Many recursiva-------
    @ManyToOne
    @JoinColumn(name = "idRelacionada")
    private Tematica tematica;

    @OneToMany(mappedBy = "tematica")
    private List<Tematica> tematicas;
    //--------------------------------------


}