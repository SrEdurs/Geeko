package es.geeko.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
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

    @ManyToMany(mappedBy = "tematicas")
    private List<Producto> productos;

    //Relación One to Many recursiva-------
    @ManyToOne
    @JoinColumn(name = "idRelacionada")
    private Tematica tematica;

    @OneToMany(mappedBy = "tematica")
    private List<Tematica> tematicas;
    //--------------------------------------

    public Tematica() {
    }

    public Tematica(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        //this.idRelacionada = 0L;
    }

    @Override
    public String toString() {
        return "Tematica{" +
                "id=" + id +
                ", nombre='" + nombre +
                '}';
    }
}