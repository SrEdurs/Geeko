package es.geeko.repository;

import es.geeko.dto.ProductoDto;
import es.geeko.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    //Libro
    List<Producto> findProductosByLibroAndActivoAndGeekoIs(int libro, int activo, int geeko);

    //Pelicula
    List<Producto> findProductosByPeliculaAndActivoAndGeekoIs(int pelicula, int activo, int geeko);

    //Serie
    List<Producto> findProductosBySerieAndActivoAndGeekoIs(int serie, int activo, int geeko);

    //Videojuego
    List<Producto> findProductosByVideojuegoAndActivoAndGeekoIs(int videojuego, int activo, int geeko);

    //Perfil
    List<Producto> findProductosByTituloIsNotLikeAndGeekoIs(String ignorar, int geeko);

    //UPDATE `geeko`.`productos` SET `id_usuario_propietario` = '1' WHERE (`id` = '57');
    /*@Modifying
    @Query(value = "UPDATE `geeko`.`productos` SET `id_usuario_propietario` = ? WHERE (`id` = ?)", nativeQuery = true)
    Integer idPropietario(int idUsuario, int idProducto);*/

    //Productos de usuario iniciado
    List<Producto> findProductosByUsuarioId(int id);

    Producto findProductoByIdIs(int id);
    List<Producto> findProductosByIdIs(int id);
}
