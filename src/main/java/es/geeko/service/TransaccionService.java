package es.geeko.service;

import es.geeko.dto.TransaccionDto;
import es.geeko.model.Transaccion;
import es.geeko.repository.TransaccionRepository;
import es.geeko.service.mapper.TransaccionMapper;
import org.springframework.stereotype.Service;

@Service
public class TransaccionService extends AbstractBusinessService<Transaccion, Integer, TransaccionDto, TransaccionRepository, TransaccionMapper> {

    public TransaccionService(TransaccionRepository transaccionRepository, TransaccionMapper mapper) {
        super(transaccionRepository, mapper);
    }
}
