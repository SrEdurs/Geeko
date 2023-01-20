package es.geeko.model;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="nombre", length = 60)
    private String nombre;

    @Column(name="apellidos", length = 60)
    private String apellidos;

    @Column(name="usuario", length = 30)
    @NotNull
    private String usuario;

    @Column(name="emilio", length = 100)
    @NotNull
    private String emilio;

    @Column(name="clave", length = 255)
    @NotNull
    private String clave;

    @Column(name="avatar", length = 255)
    private String avatar;

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
    private Date fecha_alta;

    @Column(name="valoracion_media")
    private double valoracion_media;

    @Column(name="admin", length = 1)
    private int admin;

    @Column(name="activo", length = 1)
    private int activo;

    @Column(name="biografia", length = 160)
    private String biografia;

    @Column (name="reportado", length = 1)
    private int reportado;


    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;

    @OneToMany(mappedBy = "usuario")
    private List<Mensaje> mensajes;

    @ManyToMany
    @JoinTable(
            name="Preferencias",
            joinColumns = @JoinColumn(name="idUsuario"),
            inverseJoinColumns = @JoinColumn(name="idTematica")
    )
    private List<Tematica> tematicas;

    @ManyToMany
    @JoinTable(
            name="Usuarios_has_Transacciones",
            joinColumns = @JoinColumn(name="Usuarios_id"),
            inverseJoinColumns = @JoinColumn(name="Transacciones_id")
    )
    private List<Transaccion> transacciones;

    @ManyToMany
    @JoinTable(
            name="Chats_has_Usuarios",
            joinColumns = @JoinColumn(name="Destinatarios_id"),
            inverseJoinColumns = @JoinColumn(name="Chats_id")
    )
    private List<Chat> chats;

    @ManyToMany
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
   @ManyToMany
   @JoinTable(
           name="Seguimientos",
           joinColumns = @JoinColumn(name="idSeguidor"),
           inverseJoinColumns = @JoinColumn(name="idSeguido")
   )
   private List<Usuario> seguimientos;

    @ManyToMany
    @JoinTable(
            name="Seguimientos",
            joinColumns = @JoinColumn(name="idSeguido"),
            inverseJoinColumns = @JoinColumn(name="idSeguidor")
    )
    private List<Usuario> seguidos;
    //----------------------------------------------------------------------------


    public Usuario() {
    }

    public Usuario(long id, String nombre, String apellidos, String usuario, String emilio, String clave, String avatar, String direccion1, String direccion2, String cp, String poblacion, String provincia, String tlf, int verificacion2pasos, Date fecha_alta, double valoracion_media, int admin, int activo, String biografia, int reportado, List<Comentario> comentarios, List<Producto> productos, List<Mensaje> mensajes, List<Tematica> tematicas, List<Transaccion> transacciones, List<Chat> chats, List<Reporte> usuariosReportados, List<Reporte> reportes, List<Usuario> seguimientos, List<Usuario> seguidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.emilio = emilio;
        this.clave = clave;
        this.avatar = avatar;
        this.direccion1 = direccion1;
        this.direccion2 = direccion2;
        this.cp = cp;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.tlf = tlf;
        this.verificacion2pasos = verificacion2pasos;
        this.fecha_alta = fecha_alta;
        this.valoracion_media = valoracion_media;
        this.admin = admin;
        this.activo = activo;
        this.biografia = biografia;
        this.reportado = reportado;
        this.comentarios = comentarios;
        this.productos = productos;
        this.mensajes = mensajes;
        this.tematicas = tematicas;
        this.transacciones = transacciones;
        this.chats = chats;
        this.usuariosReportados = usuariosReportados;
        this.reportes = reportes;
        this.seguimientos = seguimientos;
        this.seguidos = seguidos;
    }
}


