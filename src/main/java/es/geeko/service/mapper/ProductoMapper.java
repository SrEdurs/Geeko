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
        modelMapper.map(entidad,dto);
        return dto;
    }


    @Override
    public Producto toEntity(ProductoDto productoDto){

        //Convertir de dto a entidad
        final Producto entidad = new Producto();
        entidad.setId(productoDto.getId());
        entidad.setTitulo(productoDto.getTitulo());
        entidad.setImagen(productoDto.getImagen());
        entidad.setDescripcion(productoDto.getDescripcion());
        entidad.setPrecio(productoDto.getPrecio());
        entidad.setPuntuacion(productoDto.getPuntuacion());
        entidad.setVideojuego(productoDto.getVideojuego());
        entidad.setLibro(productoDto.getLibro());
        entidad.setPelicula(productoDto.getPelicula());
        entidad.setReportado(productoDto.getReportado());
        entidad.setActivo(productoDto.getActivo());
        entidad.setFechaSubida(productoDto.getFechaSubida());
        return entidad;
    }

    public ProductoMapper() {
    }
}
