package es.geeko.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransaccionDto {

    private long id;
    private int idCliente;
    private int idVendedor;
    private double valoracionTransaccion;

}
