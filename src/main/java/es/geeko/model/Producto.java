package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "productos")

public class Producto {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="título", length = 60)
    @NotNull
    private String titulo;

    @Column(name="imagen", length = 200)
    private String imagen;

    @Column(name="descripción", length = 250) //tipo TEXT(?) no sé cuánto ponerle
    @NotNull
    private String descripcion;

    @Column(name="precio")
    private double precio;

    @Column(name="puntuación")
    private int puntuacion;

    @Column(name="videojuego")
    private int videojuego;

    @Column(name="libro")
    private int libro;

    @Column(name="película")
    private int pelicula;

    @Column(name="idUsuarioPropietario")
    @NotNull
    private int idUsuarioPropietario;

    @Column(name="reportado", length = 1)
    private int reportado;

    @Column(name="activo", length = 1)
    @NotNull
    private int activo;

    @Column(name="fechaSubida")
    private Date fechaSubida;

    public Producto() {
    }

    public Producto(int id, String titulo, String imagen, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getVideojuego() {
        return videojuego;
    }

    public void setVideojuego(int videojuego) {
        this.videojuego = videojuego;
    }

    public int getLibro() {
        return libro;
    }

    public void setLibro(int libro) {
        this.libro = libro;
    }

    public int getPelicula() {
        return pelicula;
    }

    public void setPelicula(int pelicula) {
        this.pelicula = pelicula;
    }

    public int getIdUsuarioPropietario() {
        return idUsuarioPropietario;
    }

    public void setIdUsuarioPropietario(int idUsuarioPropietario) {
        this.idUsuarioPropietario = idUsuarioPropietario;
    }

    public int getReportado() {
        return reportado;
    }

    public void setReportado(int reportado) {
        this.reportado = reportado;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Date getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", título='" + titulo + '\'' +
                ", imagen='" + imagen + '\'' +
                ", descripción='" + descripcion + '\'' +
                ", precio='" + precio + '\'' +
                ", puntuación='" + puntuacion + '\'' +
                ", videojuego='" + videojuego + '\'' +
                ", libro='" + libro + '\'' +
                ", película='" + pelicula + '\'' +
                ", idUsuarioPropietario='" + idUsuarioPropietario + '\'' +
                ", reportado='" + reportado + '\'' +
                ", activo='" + activo + '\'' +
                ", fechaSubida='" + fechaSubida + '\'' +
                '}';
    }
}