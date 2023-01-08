package es.geeko.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos_reportados")
public class ProductoReporte {

    @Id
    @Column(name = "idProductoReportado")
    private int idProductoReportado;

    @Id
    @Column(name = "idReporte")
    private int idReporte;

    @ManyToOne
    @JoinColumn(name = "id")
    Producto producto;

    @ManyToOne
    @JoinColumn(name = "id")
    Reporte reporte;

    public ProductoReporte(int idProductoReportado, int idReporte) {
        this.idProductoReportado = idProductoReportado;
        this.idReporte = idReporte;
    }

    public int getIdProductoReportado() {
        return idProductoReportado;
    }
    public void setIdProductoReportado(int id) {
        this.idProductoReportado = id;
    }

    public int getIdReporte() {
        return idReporte;
    }
    public void setIdReporte(int id) {
        this.idReporte = id;
    }

    public Producto producto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Reporte getReporte() {
        return reporte;
    }
    public void setReporte(Reporte reporte) {
        this.reporte = reporte;
    }
}
