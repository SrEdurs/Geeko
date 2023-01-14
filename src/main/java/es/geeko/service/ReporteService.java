package es.geeko.service;

import es.geeko.dto.ReporteDto;
import es.geeko.model.Reporte;
import es.geeko.repository.ReporteRepository;
import es.geeko.service.mapper.ReporteMapper;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    private final ReporteRepository reporteRepository;
    private final ReporteMapper reporteMapper;

    public ReporteService(ReporteRepository reporteRepository, ReporteMapper reporteMapper) {
        this.reporteRepository = reporteRepository;
        this.reporteMapper = reporteMapper;
    }

    public ReporteDto save(ReporteDto reporteDto){
        final Reporte entidad = reporteMapper.toEntity(reporteDto);
        Reporte entidadReporteGuardada = reporteRepository.save(entidad);
        return reporteMapper.toDto(entidadReporteGuardada);
    }
}
