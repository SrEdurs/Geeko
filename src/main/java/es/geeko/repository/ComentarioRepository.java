package es.geeko.repository;

import es.geeko.model.Comentario;
import es.geeko.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepository  extends JpaRepository<Comentario,Long> {

    //Lista de comentarios de un usuario
    List<Comentario> findComentarioByUsuarioAndActivoOrderByIdDesc(Usuario usuario, int activo);

    //Comentario y lista por ID
    Comentario findComentariosByIdIs(long id);
    List<Comentario> findComentarioById(long id);
    Optional <Comentario> findComentarioByIdIs(long id);

    //Comentarios reportados
    List<Comentario> findComentariosByReportadoIsAndActivoIs(int reportado, int activo);

    //Comentarios de un producto
    List<Comentario> findComentariosByProductoIdAndActivoIsOrderByIdDesc(long id, int activo);

    //Comentarios de tus seguidos
    List<Comentario> findTop8ComentariosByActivoIsAndUsuarioInOrderByIdDesc (int activo, List<Usuario> usuarios);
}
