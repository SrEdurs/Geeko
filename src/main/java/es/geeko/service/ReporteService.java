package es.geeko.service;

import es.geeko.dto.ReporteDto;
import es.geeko.model.Comentario;
import es.geeko.model.Reporte;
import es.geeko.repository.ReporteRepository;
import es.geeko.service.mapper.ReporteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ReporteService extends AbstractBusinessService<Reporte, Integer, ReporteDto, ReporteRepository, ReporteMapper> {

    public ReporteService(ReporteRepository repo, ReporteMapper mapper) {
        super(repo, mapper);
    }

}
