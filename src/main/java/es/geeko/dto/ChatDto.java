package es.geeko.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {

    private long id;
    private int idDestinatario;
    private int idRemitente;
    private String titulo;
    private String imagen;
    private String descripcion;
    private int activo;
    private List<MensajeDto> mensajes;
    private List<UsuarioDto> usuarios;

}
