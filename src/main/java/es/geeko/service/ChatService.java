package es.geeko.service;

import es.geeko.dto.ChatDto;
import es.geeko.model.Chat;
import es.geeko.repository.ChatRepository;
import es.geeko.service.mapper.ChatMapper;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
        this.chatMapper = new ChatMapper();
    }

    public ChatDto save(ChatDto chatDto){
        //Traduzco del dto con datos de entrada a la entidad
        final Chat entidad = chatMapper.toEntity(chatDto);

        //Guardo en la base de datos
        Chat entidadChatGuardada = chatRepository.save(entidad);

        //Traducir la entidad a DTO para devolver el DTO
        return chatMapper.toDto(entidadChatGuardada);
    }
}
