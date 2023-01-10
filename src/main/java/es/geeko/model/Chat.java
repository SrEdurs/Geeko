package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

    /*
    @JoinTable(name = "chats_has_usuarios",
            joinColumns = @JoinColumn(name = "Chats_id"),
            inverseJoinColumns = @JoinColumn(name = "Destinatarios_id"))
    private List<Usuario> usuarios = new ArrayList<>();

    @OneToMany(mappedBy = "mensajes")
    List<Mensaje> mensajes;
*/
    public Chat() {
    }

    public Chat(int id, int idDestinatario, int idRemitente, String titulo) {
        this.id = id;
        this.idDestinatario = idDestinatario;
        this.idRemitente = idRemitente;
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", idDestinatario=" + idDestinatario +
                ", idRemitente=" + idRemitente +
                ", titulo='" + titulo + '\'' +
                ", imagen='" + imagen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", activo=" + activo +
                ", mensajes=" + mensajes +
                '}';
    }
}
