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
@Table(name = "chats")
public class Chat {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="idDestinatario", nullable = false)

    private int idDestinatario;

    @Column(name="idRemitente", nullable = false)
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

}
