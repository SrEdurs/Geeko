package es.geeko.repository;

import es.geeko.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository  extends JpaRepository<Comentario,Integer> {
}
