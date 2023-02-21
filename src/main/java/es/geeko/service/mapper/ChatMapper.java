package es.geeko.service.mapper;

import es.geeko.dto.ChatDto;
import es.geeko.model.Chat;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatMapper extends AbstractServiceMapper<Chat, ChatDto> {

    //Convertir de entidad a dto
    @Override
    public ChatDto toDto(Chat entidad){
        final ChatDto dto = new ChatDto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(entidad,dto);
        return dto;
    }

    //Convertir de dto a entidad
    @Override
    public Chat toEntity(ChatDto dto) {
        final Chat entidad = new Chat();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto, entidad);
        return entidad;
    }

    public ChatMapper() {
    }
}
