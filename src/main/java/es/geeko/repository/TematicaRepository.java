package es.geeko.repository;

import es.geeko.model.Tematica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TematicaRepository extends JpaRepository<Tematica,Long> {
}
