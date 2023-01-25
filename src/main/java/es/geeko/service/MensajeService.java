package es.geeko.service;

import es.geeko.dto.MensajeDto;
import es.geeko.model.Mensaje;
import es.geeko.repository.MensajeRepository;
import es.geeko.service.mapper.MensajeMapper;
import org.springframework.stereotype.Service;

@Service
public class MensajeService extends AbstractBusinessService<Mensaje, Integer, MensajeDto, MensajeRepository, MensajeMapper> {

    public MensajeService(MensajeRepository repo, MensajeMapper mapper) {
        super(repo, mapper);
    }
}
