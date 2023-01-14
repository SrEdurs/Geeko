package es.geeko.dto;

import es.geeko.model.Chat;
import es.geeko.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class MensajeDto {

    private long id;
    private Date fecha;
    private String texto;
    private String imagen;
    private Usuario usuario;
    private Chat chat;

    public MensajeDto() {
    }

    public MensajeDto(long id, Date fecha, String texto, String imagen, Usuario usuario, Chat chat) {
        this.id = id;
        this.fecha = fecha;
        this.texto = texto;
        this.imagen = imagen;
        this.usuario = usuario;
        this.chat = chat;
    }
}
