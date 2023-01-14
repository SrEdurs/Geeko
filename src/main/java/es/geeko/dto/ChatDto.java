package es.geeko.dto;

import es.geeko.model.Mensaje;
import es.geeko.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatDto {

    private long id;
    private int idDestinatario;
    private int idRemitente;
    private String titulo;
    private String imagen;
    private String descripcion;
    private int activo;
    private List<Mensaje> mensajes;
    private List<Usuario> usuarios;

    public ChatDto() {
    }

    public ChatDto(long id, int idDestinatario, int idRemitente, String titulo, String imagen, String descripcion, int activo, List<Mensaje> mensajes, List<Usuario> usuarios) {
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
