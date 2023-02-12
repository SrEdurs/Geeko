package es.geeko.repository;

import es.geeko.dto.ProductoDto;
import es.geeko.model.Comentario;
import es.geeko.model.Producto;
import es.geeko.model.Tematica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    //Libro
    List<Producto> findProductosByLibroAndActivoAndGeekoIsOrderById(int libro, int activo, int geeko);

    //Pelicula
    List<Producto> findProductosByPeliculaAndActivoAndGeekoIsOrderById(int pelicula, int activo, int geeko);

    //Serie
    List<Producto> findProductosBySerieAndActivoAndGeekoIsOrderById(int serie, int activo, int geeko);

    //Videojuego
    List<Producto> findProductosByVideojuegoAndActivoAndGeekoIsOrderById(int videojuego, int activo, int geeko);

    //Te interesa
    List<Producto> findProductosByTematicaIsInAndGeekoIsAndActivoIs(List<Tematica> tematicas, int geeko, int activo);

    //Productos de usuario iniciado
    List<Producto> findProductosByUsuarioId(int id);

    Producto findProductoByIdIs(int id);
    List<Producto> findProductosByIdIs(int id);

    //Productos reportados
    List<Producto> findProductosByReportadoIsOrderById(int reportado);

    ProductoDto findProductoById(int id);
}
