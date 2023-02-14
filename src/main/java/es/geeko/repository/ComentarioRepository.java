package es.geeko.repository;

import es.geeko.model.Comentario;
import es.geeko.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository  extends JpaRepository<Comentario,Integer> {

    //Lista de comentarios de un usuario
    List<Comentario> findComentarioByUsuarioAndActivo(Usuario usuario, int activo);

    //Comentario y lista por ID
    Comentario findComentariosByIdIs(int id);
    List<Comentario> findComentarioById(int id);

    //Comentarios reportados
    List<Comentario> findComentariosByReportadoIsAndActivoIs(int reportado, int activo);

    //Comentarios de un producto
    List<Comentario> findComentariosByProductoIdAndActivoIs(int id, int activo);
}
