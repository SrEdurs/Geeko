package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "chats")
public class Chat {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    @OneToMany(mappedBy = "chat")
    private List<Mensaje> mensajes;

    @ManyToMany(mappedBy = "chats")
    private List<Usuario> usuarios;

    public Chat() {
    }

    public Chat(long id, int idDestinatario, int idRemitente, String titulo, String imagen, String descripcion, int activo, List<Mensaje> mensajes, List<Usuario> usuarios) {
        this.id = id;
        this.idDestinatario = idDestinatario;
        this.idRemitente = idRemitente;
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.activo = activo;
        this.mensajes = mensajes;
        this.usuarios = usuarios;
    }
}
