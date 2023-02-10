package es.geeko.repository;

import es.geeko.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReporteRepository extends JpaRepository<Reporte,Integer> {

    Reporte findReporteByIdIs(int id);
}
