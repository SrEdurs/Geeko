package es.geeko.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios_reportes")
public class UsuarioReporte {

    @Id
    @Column(name = "idUsuarioReportado")
    private int idUsuarioReportado;

    @Id
    @Column(name = "idReporte")
    private int idReporte;

    @ManyToOne
    @JoinColumn(name = "id")
    Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id")
    Reporte reporte;

    public UsuarioReporte(int idUsuarioReportado, int idReporte) {
        this.idUsuarioReportado = idUsuarioReportado;
        this.idReporte = idReporte;
    }

    public int getIdUsuarioReportado() {
        return idUsuarioReportado;
    }
    public void setIdUsuarioReportado(int id) {
        this.idUsuarioReportado = id;
    }

    public int getIdReporte() {
        return idReporte;
    }
    public void setIdReporte(int id) {
        this.idReporte = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Reporte getReporte() {
        return reporte;
    }
    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }
}
