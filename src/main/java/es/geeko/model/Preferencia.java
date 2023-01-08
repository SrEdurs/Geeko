package es.geeko.model;

import jakarta.persistence.*;

@Entity
@Table(name = "preferencias")
public class Preferencia {

    @Id
    @Column(name = "idUsuario")
    private int idUsuario;

    @Id
    @Column(name = "idTematica")
    private int idTematica;

    @ManyToOne
    @JoinColumn(name = "id")
    Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id")
    Tematica tematica;

    public Preferencia(int idUsuario, int idTematica) {
        this.idUsuario = idUsuario;
        this.idTematica = idTematica;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int id) {
        this.idUsuario = id;
    }

    public int getIdTematica() {
        return idTematica;
    }
    public void setIdTematica(int id) {
        this.idTematica = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Tematica getTematica() {
        return tematica;
    }
    public void setTematica(Tematica tematica) {
        this.tematica = tematica;
    }
}