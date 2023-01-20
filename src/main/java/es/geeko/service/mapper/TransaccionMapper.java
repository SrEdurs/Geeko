package es.geeko.service.mapper;

import es.geeko.dto.TransaccionDto;
import es.geeko.model.Transaccion;
import org.springframework.stereotype.Service;

@Service
public class TransaccionMapper extends AbstractServiceMapper<Transaccion, TransaccionDto> {

    @Override
    public TransaccionDto toDto(Transaccion transaccion){

        //Convertir entidad a dto
        final TransaccionDto dto = new TransaccionDto();
        dto.setId(transaccion.getId());
        dto.setIdCliente(transaccion.getIdCliente());
        dto.setIdVendedor(transaccion.getIdVendedor());
        dto.setValoracionTransaccion(transaccion.getValoracionTransaccion());
        return dto;
    }

    @Override
    public Transaccion toEntity(TransaccionDto transaccionDto){

        //Convertir de dto a entidad
        final Transaccion entidad = new Transaccion();
        entidad.setId(transaccionDto.getId());
        entidad.setIdCliente(transaccionDto.getIdCliente());
        entidad.setIdVendedor(transaccionDto.getIdVendedor());
        entidad.setValoracionTransaccion(transaccionDto.getValoracionTransaccion());
        return entidad;
    }

    public TransaccionMapper() {
    }
}
