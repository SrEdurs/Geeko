package es.geeko.dto;

import es.geeko.model.Chat;
import es.geeko.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDto {

    private long id;
    private Date fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    private String texto;
    private String imagen;
    private Usuario usuario;
    private Chat chat;

}
