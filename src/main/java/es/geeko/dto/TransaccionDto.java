package es.geeko.dto;

import es.geeko.model.Producto;
import es.geeko.model.Usuario;
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
    private Producto producto;
    private List<Usuario> usuarios;

}
