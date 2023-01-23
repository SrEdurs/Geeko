package es.geeko.dto;

import lombok.*;

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

}
