package es.geeko.service.mapper;

import es.geeko.dto.ProductoDto;
import es.geeko.model.Producto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductoMapper extends AbstractServiceMapper<Producto, ProductoDto> {

    //Convertir de entidad a dto
    @Override
    public ProductoDto toDto(Producto entidad){
        final ProductoDto dto = new ProductoDto();
        ModelMapper modelMapper = new ModelMapper();
        if(entidad.getImagen() == null) {
            entidad.setImagen("/static/imagenes/noimage.jpg");
        }
        modelMapper.map(entidad,dto);
        return dto;
    }


    @Override
    public Producto toEntity(ProductoDto dto){

        //Convertir de dto a entidad
        final Producto entidad = new Producto();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(dto, entidad);
        return entidad;
    }

    public ProductoMapper() {
    }
}
