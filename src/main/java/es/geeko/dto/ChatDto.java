package es.geeko.dto;

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

    public ChatDto() {
    }

    public ChatDto(long id, int idDestinatario, int idRemitente, String titulo, String imagen, String descripcion, int activo) {
        this.id = id;
        this.idDestinatario = idDestinatario;
        this.idRemitente = idRemitente;
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.activo = activo;
    }
}
