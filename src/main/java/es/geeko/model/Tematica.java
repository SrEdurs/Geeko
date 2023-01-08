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
    private int id;

    @Column(name = "nombre", length = 45)
    private String nombre;

    @Column(name = "idRelacionada")
    private int idRelacionada;

    @ManyToMany(mappedBy = "tematicas")
    private List<Usuario> usuarios = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tematica_productos",
            joinColumns = @JoinColumn(name = "Tematica_id"),
            inverseJoinColumns = @JoinColumn(name = "Productos_id"))
    private List<Producto> productos = new ArrayList<>();

    public Tematica() {
    }
    public Tematica(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getIdRelacionada() {
        return idRelacionada;
    }
    public void setIdRelacionada(int idRelacionada) {
        this.idRelacionada = idRelacionada;
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