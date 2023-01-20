package es.geeko.model;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="fecha")
    @NotNull
    private Date fecha;

    @Column(name="texto", length = 100)
    private String texto;

    @Column(name="imagen", length = 255)
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "idRemitente")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idChat")
    private Chat chat;


    public Mensaje() {
    }

    public Mensaje(long id, int idRemitente, Date fecha, String texto, int idChat) {
        this.id = id;
        this.fecha = fecha;
        this.texto = texto;
    }


    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                '}';
    }
}
