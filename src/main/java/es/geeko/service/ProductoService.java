package es.geeko.service;

import es.geeko.dto.ProductoDto;
import es.geeko.dto.UsuarioDto;
import es.geeko.model.Producto;
import es.geeko.model.Usuario;
import es.geeko.repository.ProductoRepository;
import es.geeko.service.mapper.ProductoMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductoService  extends AbstractBusinessService<Producto, Integer, ProductoDto, ProductoRepository, ProductoMapper> {

    public ProductoService(ProductoRepository repo, ProductoMapper mapper) {
        super(repo, mapper);
    }

}
