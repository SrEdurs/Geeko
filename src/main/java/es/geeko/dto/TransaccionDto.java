package es.geeko.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionDto {

    private long id;
    private int idCliente;
    private int idVendedor;
    private double valoracionTransaccion;
    private ProductoDto producto;
    private List<UsuarioDto> usuarios;

}
