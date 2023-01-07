package es.geeko.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tematicas")
public class Tematica {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre", length = 45)
    private String nombre;
    //Falta idRelacionada (se devuelve a su misma tabla y npi) HELP

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

@Override
public String toString() {
    return "Temática{" +
            "id=" + id +
            ", nombre=" + nombre + '\'' +
            '}';
 }
}