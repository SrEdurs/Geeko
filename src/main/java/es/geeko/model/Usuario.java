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
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="usuario", length = 60)
    private String nick;

    @Column(name="nombre", length = 60)
    private String nombre;

    @Column(name="apellidos", length = 60)
    private String apellidos;


    @Column(name="emilio", length = 100, nullable = false, unique = true)
    private String emilio;

    @Column(name="clave", length = 255, nullable = false)
    private String clave;

    @Column(name="avatar", length = 255)
    private String avatar = "/imagenes/noimage.jpg";

    @Column(name="direccion1", length = 50)
    private String direccion1;

    @Column(name="direccion2", length = 70)
    private String direccion2;

    @Column(name="cp", length = 5)
    private String cp;

    @Column(name="poblacion", length = 60)
    private String poblacion;

    @Column(name="provincia", length = 45)
    private String provincia;

    @Column(name="tlf", length = 15)
    private String tlf;

    @Column(name="verificacion2pasos", length = 1)
    private int verificacion2pasos;

    @Column(name="fecha_alta")
    private Date fecha_alta = new java.sql.Date(Calendar.getInstance().getTime().getTime());

    @Column(name="valoracion_media")
    private double valoracion_media;

    @Column(name="activo", length = 1)
    private int activo = 1;

    @Column(name="biografia", length = 160)
    private String biografia;

    @Column (name="reportado", length = 1)
    private int reportado = 0;

    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(
            name="roles",
            joinColumns = @JoinColumn(name="user_id")
    )
    @Column(name="user_role")
    private List<String> roles;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;

    @OneToMany(mappedBy = "usuarioLike")
    private List<Like> likes;

    @OneToMany(mappedBy = "usuarioPuntua")
    private List<Puntuacion> puntuaciones;

    @OneToMany(mappedBy = "usuarioPuntuado")
    private List<Puntuacion> puntuacionesRecibidas;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name="Preferencias",
            joinColumns = @JoinColumn(name="idUsuario"),
            inverseJoinColumns = @JoinColumn(name="idTematica")
    )
    private List<Tematica> tematicas;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name="Usuarios_has_Transacciones",
            joinColumns = @JoinColumn(name="Usuarios_id"),
            inverseJoinColumns = @JoinColumn(name="Transacciones_id")
    )
    private List<Transaccion> transacciones;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name="Usuarios_Reportes",
            joinColumns = @JoinColumn(name="idUsuarioReportado"),
            inverseJoinColumns = @JoinColumn(name="idReporte")
    )
    private List<Reporte> usuariosReportados;

    @OneToMany(mappedBy = "usuario")
    private List<Reporte> reportes;


    //Relación Many to Many recursiva, la tabla "Seguimientos" es la intermedia
    //Vista en https://stackoverflow.com/questions/1656113/hibernate-recursive-many-to-many-association-with-the-same-entity
   @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
   @JoinTable(
           name="Seguimientos",
           joinColumns = @JoinColumn(name="idSeguidor"),
           inverseJoinColumns = @JoinColumn(name="idSeguido")
   )
   private List<Usuario> seguimientos;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name="Seguimientos",
            joinColumns = @JoinColumn(name="idSeguido"),
            inverseJoinColumns = @JoinColumn(name="idSeguidor")
    )
    private List<Usuario> seguidos;

    //------------------------------------------------------------------------------------------------------------------------------

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name="UsuariosChats",
            joinColumns = @JoinColumn(name="idUsuario"),
            inverseJoinColumns = @JoinColumn(name="idChat")
    )
    private List<Chat> chats;

}


