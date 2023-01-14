package es.geeko.service.mapper;

import es.geeko.dto.ProductoDto;
import es.geeko.model.Producto;

public class ProductoMapper {

    //Convertir de entidad a dto
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
        dto.setComentario(producto.getComentario());
        dto.setUsuario(producto.getUsuario());
        dto.setTematicas(producto.getTematicas());
        dto.setProductosReportados(producto.getProductosReportados());
        return dto;
    }


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
        entidad.setComentario(productoDto.getComentario());
        entidad.setUsuario(productoDto.getUsuario());
        entidad.setTematicas(productoDto.getTematicas());
        entidad.setProductosReportados(productoDto.getProductosReportados());
        return entidad;
    }

    public ProductoMapper() {
    }
}
