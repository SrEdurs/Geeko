package es.geeko.repository;

import es.geeko.dto.ProductoDto;
import es.geeko.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
}
