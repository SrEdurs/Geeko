package es.geeko.dto;

import es.geeko.model.Producto;
import es.geeko.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
public class TransaccionDto {

    private long id;
    private int idCliente;
    private int idVendedor;
    private double valoracionTransaccion;

    public TransaccionDto() {
    }

    public TransaccionDto(long id, int idCliente, int idVendedor, double valoracionTransaccion) {
        this.id = id;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.valoracionTransaccion = valoracionTransaccion;
    }
}
