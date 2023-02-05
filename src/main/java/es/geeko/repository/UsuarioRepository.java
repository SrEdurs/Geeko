package es.geeko.repository;

import es.geeko.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    Optional<Usuario> findUsuarioByEmilio(String emilio);

}
