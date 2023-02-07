package es.geeko.repository;

import es.geeko.dto.UsuarioDto;
import es.geeko.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    Optional<Usuario> findUsuarioByEmilio(String emilio);

    Usuario getUsuarioByIdIs(int id);
    //UsuarioDto findUsuarioByIdIs(int id);

    String findUsuarioByAvatar(String avatar);

}
