package es.geeko.service;

import es.geeko.dto.MensajeDto;
import es.geeko.model.Mensaje;
import es.geeko.repository.MensajeRepository;
import es.geeko.service.mapper.MensajeMapper;

public class MensajeService extends  AbstractBusinessService<Mensaje, Integer, MensajeDto, MensajeRepository, MensajeMapper> {

    public MensajeService(MensajeRepository mensajeRepository, MensajeMapper mapper) { super(mensajeRepository, mapper);
    }
}
