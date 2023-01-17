package es.geeko.service;

import es.geeko.dto.MensajeDto;
import es.geeko.model.Mensaje;
import es.geeko.repository.MensajeRepository;
import es.geeko.service.mapper.MensajeMapper;
import org.springframework.stereotype.Service;

@Service
public class MensajeService {

    private final MensajeRepository mensajeRepository;
    private final MensajeMapper mensajeMapper;

    public MensajeService(MensajeRepository mensajeRepository) {
        this.mensajeRepository = mensajeRepository;
        this.mensajeMapper = new MensajeMapper();
    }

    public MensajeDto save(MensajeDto mensajeDto){
        final Mensaje entidad = mensajeMapper.toEntity(mensajeDto);
        Mensaje entidadMensajeGuardada = mensajeRepository.save(entidad);
        return mensajeMapper.toDto(entidadMensajeGuardada);
    }
}
