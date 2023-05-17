package es.geeko.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.geeko.model.Producto;
import es.geeko.model.Puntuacion;
import es.geeko.model.Usuario;


public interface PuntuacionRepository extends JpaRepository<Puntuacion,Long>{

Puntuacion findPuntuacionByProductoPuntuadoAndUsuarioPuntua(Producto productoPuntuado, Usuario usuarioPuntua);

}
