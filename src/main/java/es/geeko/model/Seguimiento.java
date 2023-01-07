package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seguimientos")
public class Seguimiento {

    @Id
    @Column(name="idSeguidor")
    private int idSeguidor;

    @Column(name="idSeguido")
    @NotNull
    private int idSeguido;

    @ManyToMany(mappedBy = "seguimientos")
    private List<Usuario> usuarios = new ArrayList<>();
}
