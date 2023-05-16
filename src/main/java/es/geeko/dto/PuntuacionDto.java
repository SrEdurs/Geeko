package es.geeko.dto;

import es.geeko.model.Producto;
import es.geeko.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PuntuacionDto {

    private long id;
    private double puntuacion;
    private Usuario usuarioPuntua;
    private Producto productoPuntuado;
    private Usuario usuarioPuntuado;

}
