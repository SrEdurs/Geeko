package es.geeko.repository;

import es.geeko.dto.ProductoDto;
import es.geeko.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    //Libro
    List<Producto> findProductosByLibroAndActivoAndUsuarioIsNotNull(int libro, int activo);
    List<Producto> findProductosByLibroAndUsuarioIsNull(int libro);

    //Pelicula
    List<Producto> findProductosByPeliculaAndActivoAndUsuarioIsNotNull(int pelicula, int activo);
    List<Producto> findProductosByPeliculaAndUsuarioIsNull(int pelicula);

    //Serie
    List<Producto> findProductosBySerieAndActivoAndUsuarioIsNotNull(int serie, int activo);
    List<Producto> findProductosBySerieAndUsuarioIsNull(int serie);

    //Videojuego
    List<Producto> findProductosByVideojuegoAndActivoAndUsuarioIsNotNull(int videojuego, int activo);
    List<Producto> findProductosByVideojuegoAndUsuarioIsNull(int videojuego);

    //Perfil
    List<Producto> findProductosByTituloIsNotLikeAndUsuarioIsNull(String ignorar);

    //UPDATE `geeko`.`productos` SET `id_usuario_propietario` = '1' WHERE (`id` = '57');
    /*@Modifying
    @Query(value = "UPDATE `geeko`.`productos` SET `id_usuario_propietario` = ? WHERE (`id` = ?)", nativeQuery = true)
    Integer idPropietario(int idUsuario, int idProducto);*/

    //Productos de usuario iniciado
    List<Producto> findProductosByUsuarioId(int id);
}
