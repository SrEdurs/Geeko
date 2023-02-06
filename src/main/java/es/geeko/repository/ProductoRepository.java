package es.geeko.repository;

import es.geeko.dto.ProductoDto;
import es.geeko.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
