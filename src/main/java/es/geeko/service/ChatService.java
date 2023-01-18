package es.geeko.service;

import es.geeko.dto.ChatDto;
import es.geeko.model.Chat;
import es.geeko.repository.ChatRepository;
import es.geeko.service.mapper.ChatMapper;
import org.springframework.stereotype.Service;

@Service
public class ChatService extends AbstractBusinessService<Chat, Integer, ChatDto, ChatRepository, ChatMapper> {

    public ChatService(ChatRepository chatRepository, ChatMapper mapper) {
        super(chatRepository, mapper);
    }
}
