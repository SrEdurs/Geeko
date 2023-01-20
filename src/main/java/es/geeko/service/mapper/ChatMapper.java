package es.geeko.service.mapper;

import es.geeko.dto.ChatDto;
import es.geeko.model.Chat;
import org.springframework.stereotype.Service;

@Service
public class ChatMapper extends AbstractServiceMapper<Chat, ChatDto> {

    //Convertir de entidad a dto
    @Override
    public ChatDto toDto(Chat chat){
        final ChatDto dto = new ChatDto();
        dto.setId(chat.getId());
        dto.setIdDestinatario(chat.getIdDestinatario());
        dto.setIdRemitente(chat.getIdRemitente());
        dto.setTitulo(chat.getTitulo());
        dto.setImagen(chat.getImagen());
        dto.setDescripcion(chat.getDescripcion());
        dto.setActivo(chat.getActivo());
        return dto;
    }

    //Convertir de dto a entidad
    @Override
    public Chat toEntity(ChatDto chatDto){
        final Chat entidad = new Chat();
        entidad.setId(chatDto.getId());
        entidad.setIdDestinatario(chatDto.getIdDestinatario());
        entidad.setIdRemitente(chatDto.getIdRemitente());
        entidad.setTitulo(chatDto.getTitulo());
        entidad.setImagen(chatDto.getImagen());
        entidad.setDescripcion(chatDto.getDescripcion());
        entidad.setActivo(chatDto.getActivo());
        return entidad;
    }

    public ChatMapper() {
    }
}
