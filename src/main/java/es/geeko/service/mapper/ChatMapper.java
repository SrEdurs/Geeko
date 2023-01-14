package es.geeko.service.mapper;

import es.geeko.dto.ChatDto;
import es.geeko.model.Chat;

public class ChatMapper {

    public ChatDto toDto(Chat chat){

        //Convertir de entidad a dto
        final ChatDto dto = new ChatDto();
        dto.setId(chat.getId());
        dto.setIdDestinatario(chat.getIdDestinatario());
        dto.setIdRemitente(chat.getIdRemitente());
        dto.setTitulo(chat.getTitulo());
        dto.setImagen(chat.getImagen());
        dto.setDescripcion(chat.getDescripcion());
        dto.setActivo(chat.getActivo());
        dto.setMensajes(chat.getMensajes());
        dto.setUsuarios(chat.getUsuarios());
        return dto;
    }

    //Convertir de dto a entidad
    public Chat toEntity(ChatDto chatDto){
        final Chat entidad = new Chat();
        entidad.setId(chatDto.getId());
        entidad.setIdDestinatario(chatDto.getIdDestinatario());
        entidad.setIdRemitente(chatDto.getIdRemitente());
        entidad.setTitulo(chatDto.getTitulo());
        entidad.setImagen(chatDto.getImagen());
        entidad.setDescripcion(chatDto.getDescripcion());
        entidad.setActivo(chatDto.getActivo());
        entidad.setMensajes(chatDto.getMensajes());
        entidad.setUsuarios(chatDto.getUsuarios());
        return entidad;
    }

    public ChatMapper() {
    }
}
