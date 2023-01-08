package es.geeko.model;

        import com.sun.istack.NotNull;
        import jakarta.persistence.*;

        import java.util.ArrayList;
        import java.util.List;

@Entity
@Table(name = "Temática_Producto")
public class Tematica_Producto {

    @Column(name="Temática_id")
    @NotNull
    private int tematica_id;

    @Column(name="Productos_id")
    @NotNull
    private int productoss_id;

    @ManyToMany(mappedBy = "Temática_Productos")
    private List<Tematica> Tematica = new ArrayList<>();
    private List<Producto> Producto = new ArrayList<>();

}
