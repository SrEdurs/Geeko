package es.geeko.model;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
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

    @Column(name= "valoracionTransaccion")
    private double valoracionTransaccion;

    @Column(name = "fecha")
    @NotNull
    private Date Fecha;

    @OneToOne
    @JoinColumn(name = "Productos_id")
    private Producto producto;

    @ManyToMany(mappedBy = "transacciones")
    private List<Usuario> usuarios;


    public Transaccion() {
    }

    public Transaccion(long id, int idCliente, int idVendedor, double valoracionTransaccion, Date fecha, Producto producto, List<Usuario> usuarios) {
        this.id = id;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.valoracionTransaccion = valoracionTransaccion;
        Fecha = fecha;
        this.producto = producto;
        this.usuarios = usuarios;
    }
}
