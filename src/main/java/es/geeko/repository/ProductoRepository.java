package es.geeko.repository;

import es.geeko.model.Producto;
import es.geeko.model.Tematica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {

    //Libro
    List<Producto> findProductosByLibroAndActivoAndGeekoIsOrderByIdDesc(int libro, int activo, int geeko);

    //Pelicula
    List<Producto> findProductosByPeliculaAndActivoAndGeekoIsOrderByIdDesc(int pelicula, int activo, int geeko);

    //Serie
    List<Producto> findProductosBySerieAndActivoAndGeekoIsOrderByIdDesc(int serie, int activo, int geeko);

    //Videojuego
    List<Producto> findProductosByVideojuegoAndActivoAndGeekoIsOrderByIdDesc(int videojuego, int activo, int geeko);

    //Te interesa
    List<Producto> findTop5ProductosByTematicaIsInAndGeekoIsAndActivoIsOrderByIdDesc(List<Tematica> tematicas, int geeko, int activo);

    //Te interesa home
    List<Producto> findTop8ProductosByTematicaIsInAndGeekoIsAndActivoIsOrderByIdDesc(List<Tematica> tematicas, int geeko, int activo);

    //Productos de usuario iniciado
    List<Producto> findProductosByUsuarioIdAndActivoIsOrderByIdDesc(int id,int activo);

    //Producto y lista por ID
    Producto findProductoByIdIs(int id);
    List<Producto> findProductosByIdIs(int id);

    //Productos reportados
    List<Producto> findProductosByReportadoIsAndActivoIs(int reportado, int activo);

}
