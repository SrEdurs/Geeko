package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Transacciones")
public class Transaccion {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
/*
    @OneToOne(mappedBy = "producto")
    private Producto producto;

    @ManyToMany(mappedBy = "transacciones")
    private List<Usuario> usuarios = new ArrayList<>();
*/
    public Transaccion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getProductos_id() {
        return Productos_id;
    }

    public void setProductos_id(int productos_id) {
        Productos_id = productos_id;
    }

    public double getValoracionTransaccion() {
        return valoracionTransaccion;
    }

    public void setValoracionTransaccion(double valoracionTransaccion) {
        this.valoracionTransaccion = valoracionTransaccion;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }
}
