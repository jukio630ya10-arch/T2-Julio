package com.cibertec.LPII_T2_Sosa_Julio.model;

import jakarta.persistence.*;
@Entity
@Table(name = "detalleboleta")
public class DetalleBoleta {

    @EmbeddedId
    private DetalleBoletaId id;

    @ManyToOne
    @MapsId("nroBoleta")
    @JoinColumn(name = "nro_boleta")
    private Boleta boleta;

    @ManyToOne
    @MapsId("idProd")
    @JoinColumn(name = "id_prod")
    private Producto producto;

    private Integer cantidad;

    @Column(name = "activo")
    private int activo = 0;

	public DetalleBoletaId getId() {
		return id;
	}

	public void setId(DetalleBoletaId id) {
		this.id = id;
	}

	public Boleta getBoleta() {
		return boleta;
	}

	public void setBoleta(Boleta boleta) {
		this.boleta = boleta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

    
    
}
