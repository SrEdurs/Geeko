package es.geeko.repository;


import es.geeko.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    Optional<Usuario> findUsuarioByEmilio(String emilio);

    Usuario getUsuarioByIdIs(int id);

    List<Usuario> findUsuariosByIdIs(int id);

    //Usuarios reportados
    List<Usuario> findUsuariosByReportadoIsAndActivoIs(int reportado, int activo);

}
