package es.geeko.service.mapper;

import es.geeko.dto.ProductoDto;
import es.geeko.model.Producto;
import org.springframework.stereotype.Service;

@Service
public class ProductoMapper extends AbstractServiceMapper<Producto, ProductoDto> {

    //Convertir de entidad a dto
    @Override
    public ProductoDto toDto(Producto producto){
        final ProductoDto dto = new ProductoDto();
        dto.setId(producto.getId());
        dto.setTitulo(producto.getTitulo());
        dto.setImagen(producto.getImagen());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setPuntuacion(producto.getPuntuacion());
        dto.setVideojuego(producto.getVideojuego());
        dto.setLibro(producto.getLibro());
        dto.setPelicula(producto.getPelicula());
        dto.setReportado(producto.getReportado());
        dto.setActivo(producto.getActivo());
        dto.setFechaSubida(producto.getFechaSubida());
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
