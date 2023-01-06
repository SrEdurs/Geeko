package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "chats")
public class Chat {
sd
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="idDestinatario")
    @NotNull
    private int idDestinatario;

    @Column(name="idRemitente")
    @NotNull
    private int idRemitente;

    @Column(name="titulo", length = 50)
    private String titulo;

    @Column(name="imagen", length = 200)
    private String imagen;

    @Column(name="descripcion", length = 45)
    private String descripcion;

    @Column(name="activo", length = 1)
    private int activo;

    @OneToMany(mappedBy = "mensajes")
    List<Mensaje> mensajes;

}
