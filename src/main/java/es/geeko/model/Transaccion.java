package es.geeko.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.util.Calendar;
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
    private long idCliente;

    @Column(name = "idVendedor", nullable = false)
    private long idVendedor;

    @Column(name = "fecha", nullable = false)
    private Date Fecha = new java.sql.Date(Calendar.getInstance().getTime().getTime());

    @OneToOne
    @JoinColumn(name = "Productos_id")
    private Producto producto;

    @ManyToMany(mappedBy = "transacciones")
    private List<Usuario> usuarios;

}
