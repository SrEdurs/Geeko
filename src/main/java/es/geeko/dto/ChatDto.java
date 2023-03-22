package es.geeko.dto;

import es.geeko.model.Mensaje;
import es.geeko.model.Usuario;
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
    private long idDestinatario;
    private long idRemitente;
    private String titulo;
    private String imagen;
    private String descripcion;
    private int activo;
    private List<Mensaje> mensajes;
    private List<Usuario> usuarios;

}
