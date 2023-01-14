package es.geeko.service.mapper;

import es.geeko.dto.MensajeDto;
import es.geeko.model.Mensaje;

public class MensajeMapper {

    public MensajeDto toDto(Mensaje mensaje){

        //Convertir entidad a dto
        final MensajeDto dto = new MensajeDto();
        dto.setId(mensaje.getId());
        dto.setFecha(mensaje.getFecha());
        dto.setTexto(mensaje.getTexto());
        dto.setUsuario(mensaje.getUsuario());
        dto.setChat(mensaje.getChat());
        return dto;
    }

    public Mensaje toEntity(MensajeDto mensajeDto){

        //Convertir de dto a entidad
        final Mensaje entidad = new Mensaje();
        entidad.setId(mensajeDto.getId());
        entidad.setFecha(mensajeDto.getFecha());
        entidad.setTexto(mensajeDto.getTexto());
        entidad.setUsuario(mensajeDto.getUsuario());
        entidad.setChat(mensajeDto.getChat());
        return entidad;
    }

    public MensajeMapper() {
    }
}
