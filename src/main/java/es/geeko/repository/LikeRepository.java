package es.geeko.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.geeko.model.Like;
import es.geeko.model.Usuario;


public interface LikeRepository extends JpaRepository<Like,Long>{

    List<Like> findByUsuarioLikeIs(Usuario usuarioLike);
    Like findById(long id);

}
