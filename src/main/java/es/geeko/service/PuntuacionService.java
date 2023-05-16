package es.geeko.service;

import es.geeko.dto.PuntuacionDto;
import es.geeko.model.Puntuacion;
import es.geeko.repository.PuntuacionRepository;
import es.geeko.service.mapper.PuntuacionMapper;
import org.springframework.stereotype.Service;

@Service
public class PuntuacionService extends AbstractBusinessService<Puntuacion, Integer, PuntuacionDto, PuntuacionRepository, PuntuacionMapper> {

    public PuntuacionService(PuntuacionRepository repo, PuntuacionMapper mapper) {
        super(repo, mapper);
    }

}
