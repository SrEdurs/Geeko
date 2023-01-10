package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Transacciones")
public class Transaccion {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="idCliente")
    @NotNull
    private int idCliente;

    @Column(name = "idVendedor")
    @NotNull
    private int idVendedor;

    @Column(name = "Productos_id")
    @NotNull
    private int Productos_id;

    @Column(name= "valoracionTransaccion")
    private double valoracionTransaccion;

    @Column(name = "fecha")
    @NotNull
    private Date Fecha;

    @ManyToMany(mappedBy = "transacciones")
    private List<Usuario> usuarios;
/*
    @OneToOne(mappedBy = "producto")
    private Producto producto;

    @ManyToMany(mappedBy = "transacciones")
    private List<Usuario> usuarios = new ArrayList<>();
*/
    public Transaccion() {
    }

    public Transaccion(long id, int idCliente, int idVendedor, int productos_id, double valoracionTransaccion, Date fecha, List<Usuario> usuarios) {
        this.id = id;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        Productos_id = productos_id;
        this.valoracionTransaccion = valoracionTransaccion;
        Fecha = fecha;
        this.usuarios = usuarios;
    }
}
