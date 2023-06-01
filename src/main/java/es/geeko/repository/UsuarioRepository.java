package es.geeko.repository;


import es.geeko.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

    Optional<Usuario> findUsuarioByEmilio(String emilio);
    Usuario getUsuarioByIdIs(long id);
    List<Usuario> findUsuariosByIdIs(long id);


    //Usuarios reportados
    List<Usuario> findUsuariosByReportadoIsAndActivoIs(int reportado, int activo);

}
