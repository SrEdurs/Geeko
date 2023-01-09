package es.geeko.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tematicas")
public class Tematica {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "idRelacionada")
    private Long idRelacionada;


    @ManyToMany(mappedBy = "tematicas")
    private List<Usuario> usuarios;

    @ManyToMany
    @JoinTable(
            name="Tematica_Productos",
            joinColumns = @JoinColumn(name="Tematica_id"),
            inverseJoinColumns = @JoinColumn(name="Productos_id")
    )
    private List<Producto> productos;

    public Tematica() {
    }
    public Tematica(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        //this.idRelacionada = 0L;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdRelacionada() {
        return idRelacionada;
    }

    public void setIdRelacionada(Long idRelacionada) {
        this.idRelacionada = idRelacionada;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
public String toString() {
    return "Temática{" +
            "id=" + id +
            ", nombre=" + nombre + '\'' +
            ", idRelacionada=" + idRelacionada + '\'' +
            '}';
 }
}