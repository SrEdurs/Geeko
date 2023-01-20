package es.geeko.service.mapper;

import es.geeko.dto.MensajeDto;
import es.geeko.model.Mensaje;
import org.springframework.stereotype.Service;

@Service
public class MensajeMapper extends AbstractServiceMapper<Mensaje, MensajeDto> {

    @Override
    public MensajeDto toDto(Mensaje mensaje){

        //Convertir entidad a dto
        final MensajeDto dto = new MensajeDto();
        dto.setId(mensaje.getId());
        dto.setFecha(mensaje.getFecha());
        dto.setTexto(mensaje.getTexto());
        return dto;
    }

    @Override
    public Mensaje toEntity(MensajeDto mensajeDto){

        //Convertir de dto a entidad
        final Mensaje entidad = new Mensaje();
        entidad.setId(mensajeDto.getId());
        entidad.setFecha(mensajeDto.getFecha());
        entidad.setTexto(mensajeDto.getTexto());
        return entidad;
    }

    public MensajeMapper() {
    }
}
