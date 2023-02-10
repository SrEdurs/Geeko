package es.geeko.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.util.Calendar;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="fecha", nullable = false)

    private Date fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());

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

}
