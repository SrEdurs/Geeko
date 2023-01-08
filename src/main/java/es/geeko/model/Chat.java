package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chats")
public class Chat {
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

    @JoinTable(name = "chats_has_usuarios",
            joinColumns = @JoinColumn(name = "Chats_id"),
            inverseJoinColumns = @JoinColumn(name = "Destinatarios_id"))
    private List<Usuario> usuarios = new ArrayList<>();

    @OneToMany(mappedBy = "mensajes")
    List<Mensaje> mensajes;

    public Chat() {
    }

    public Chat(int id, int idDestinatario, int idRemitente, String titulo) {
        this.id = id;
        this.idDestinatario = idDestinatario;
        this.idRemitente = idRemitente;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDestinatario() {
        return idDestinatario;
    }

    public void setIdDestinatario(int idDestinatario) {
        this.idDestinatario = idDestinatario;
    }

    public int getIdRemitente() {
        return idRemitente;
    }

    public void setIdRemitente(int idRemitente) {
        this.idRemitente = idRemitente;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
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
                ", usuarios=" + usuarios +
                ", mensajes=" + mensajes +
                '}';
    }
}
