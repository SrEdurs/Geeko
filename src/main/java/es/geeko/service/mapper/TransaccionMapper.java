package es.geeko.service.mapper;

import es.geeko.dto.TransaccionDto;
import es.geeko.model.Transaccion;

public class TransaccionMapper {

    public TransaccionDto toDto(Transaccion transaccion){

        //Convertir entidad a dto
        final TransaccionDto dto = new TransaccionDto();
        dto.setId(transaccion.getId());
        dto.setIdCliente(transaccion.getIdCliente());
        dto.setIdVendedor(transaccion.getIdVendedor());
        dto.setValoracionTransaccion(transaccion.getValoracionTransaccion());
        dto.setFecha(transaccion.getFecha());
        dto.setProducto(transaccion.getProducto());
        dto.setUsuarios(transaccion.getUsuarios());
        return dto;
    }

    public Transaccion toEntity(TransaccionDto transaccionDto){

        //Convertir de dto a entidad
        final Transaccion entidad = new Transaccion();
        entidad.setId(transaccionDto.getId());
        entidad.setIdCliente(transaccionDto.getIdCliente());
        entidad.setIdVendedor(transaccionDto.getIdVendedor());
        entidad.setValoracionTransaccion(transaccionDto.getValoracionTransaccion());
        entidad.setFecha(transaccionDto.getFecha());
        entidad.setProducto(transaccionDto.getProducto());
        entidad.setUsuarios(transaccionDto.getUsuarios());
        return entidad;
    }

    public TransaccionMapper() {
    }
}
