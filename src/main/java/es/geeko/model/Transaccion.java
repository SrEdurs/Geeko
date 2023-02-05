package es.geeko.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Transacciones")
public class Transaccion {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="idCliente", nullable = false)
    private int idCliente;

    @Column(name = "idVendedor", nullable = false)
    private int idVendedor;

    @Column(name= "valoracionTransaccion")
    private double valoracionTransaccion;

    @Column(name = "fecha", nullable = false)
    private Date Fecha;

    @OneToOne
    @JoinColumn(name = "Productos_id")
    private Producto producto;

    @ManyToMany(mappedBy = "transacciones")
    private List<Usuario> usuarios;

}
