package es.geeko.repository;

import es.geeko.model.Comentario;
import es.geeko.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository  extends JpaRepository<Comentario,Integer> {

    List<Comentario> findComentarioByUsuarioAndActivo(Usuario usuario, int activo);
    List<Comentario> findComentarioById(int id);
    Comentario findComentariosByIdIs(int id);
    List<Comentario> findComentariosByReportadoIsOrderById(int reportado);
    List<Comentario> findComentariosByUsuarioIdAndActivo(Usuario usuario, int activo );
}
