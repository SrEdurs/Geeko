package es.geeko.service;

import es.geeko.dto.ProductoDto;
import es.geeko.model.Producto;
import es.geeko.repository.ProductoRepository;
import es.geeko.service.mapper.ProductoMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductoService  extends AbstractBusinessService<Producto, Integer, ProductoDto, ProductoRepository, ProductoMapper> {

    public ProductoService(ProductoRepository productoRepository, ProductoMapper mapper) {
        super(productoRepository, mapper);
    }
}
