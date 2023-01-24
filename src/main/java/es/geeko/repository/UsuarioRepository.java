package es.geeko.repository;

import es.geeko.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    @Query("Select count(id) from Usuario where email= ?1 and password = ?2")
    Integer validarClave(String emilio, String clave);
}
