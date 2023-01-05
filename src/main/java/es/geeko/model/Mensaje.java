package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="idRemitente")
    @NotNull
    private int idRemitente;

    @Column(name="fecha")
    @NotNull
    private Date fecha;

    @Column(name="texto", length = 100)
    private String texto;

    @Column(name="imagen", length = 255)
    private String imagen;

    @Column(name="idChat")
    private int idChat;

    @ManyToOne
    @JoinColumn(name = "id")
    Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id")
    Chat chat;



    public Mensaje() {
    }


}
