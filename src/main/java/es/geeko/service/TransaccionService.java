package es.geeko.service;

import es.geeko.dto.TransaccionDto;
import es.geeko.model.Transaccion;
import es.geeko.repository.TransaccionRepository;
import es.geeko.service.mapper.TransaccionMapper;
import org.springframework.stereotype.Service;

@Service
public class TransaccionService {

    private final TransaccionRepository transaccionRepository;
    private final TransaccionMapper transaccionMapper;

    public TransaccionService(TransaccionRepository transaccionRepository) {
        this.transaccionRepository = transaccionRepository;
        this.transaccionMapper = new TransaccionMapper();
    }

    public TransaccionDto save(TransaccionDto transaccionDto){
        final Transaccion entidad = transaccionMapper.toEntity(transaccionDto);
        Transaccion entidadTransaccionGuardada = transaccionRepository.save(entidad);
        return transaccionMapper.toDto(entidadTransaccionGuardada);
    }
}
