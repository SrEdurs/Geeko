package es.geeko.service.mapper;

import es.geeko.dto.ComentarioDto;
import es.geeko.model.Comentario;
import org.springframework.stereotype.Service;

@Service
public class ComentarioMapper extends AbstractServiceMapper<Comentario, ComentarioDto> {

    @Override
    public ComentarioDto toDto(Comentario comentario){

        //Convertir de entidad a dto
        final ComentarioDto dto = new ComentarioDto();
        dto.setId(comentario.getId());
        dto.setTitulo(comentario.getTitulo());
        dto.setTexto(comentario.getTexto());
        dto.setImagen(comentario.getImagen());
        dto.setFecha(comentario.getFecha());
        dto.setLikes(comentario.getLikes());
        dto.setReportado(comentario.getReportado());
        dto.setActivo(comentario.getActivo());
        return dto;
    }

    @Override
    public Comentario toEntity(ComentarioDto comentarioDto){

        //Convertir de dto a entidad
        final Comentario entidad = new Comentario();
        entidad.setId(comentarioDto.getId());
        entidad.setTitulo(comentarioDto.getTitulo());
        entidad.setTexto(comentarioDto.getTexto());
        entidad.setImagen(comentarioDto.getImagen());
        entidad.setFecha(comentarioDto.getFecha());
        entidad.setLikes(comentarioDto.getLikes());
        entidad.setReportado(comentarioDto.getReportado());
        entidad.setActivo(comentarioDto.getActivo());
        return entidad;
    }

    public ComentarioMapper() {
    }
}
