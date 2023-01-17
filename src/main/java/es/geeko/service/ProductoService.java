package es.geeko.service;

import es.geeko.dto.ProductoDto;
import es.geeko.model.Producto;
import es.geeko.repository.ProductoRepository;
import es.geeko.service.mapper.ProductoMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        this.productoMapper = new ProductoMapper();
    }


    public ProductoDto save(ProductoDto productoDto){
        //Traduzco del dto con datos de entrada a la entidad
        final Producto entidad = productoMapper.toEntity(productoDto);

        //Guardo en la base de datos
        Producto entidadProductoGuardada = productoRepository.save(entidad);

        //Traducir la entidad a DTO para devolver el DTO
        return productoMapper.toDto(entidadProductoGuardada);
    }
}
