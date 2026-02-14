package com.cibertec.LPII_T2_Sosa_Julio.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "boleta")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_boleta")
    private Long nroBoleta;

    private LocalDate fecha;

    private String estado;

    @Column(name = "nom_cliente")
    private String nomCliente;

    private Double total;

    @Column(name = "activo")
    private int activo = 0;
    
    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL)
    private List<DetalleBoleta> detalles;

	public List<DetalleBoleta> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleBoleta> detalles) {
		this.detalles = detalles;
	}

	public Long getNroBoleta() {
		return nroBoleta;
	}

	public void setNroBoleta(Long nroBoleta) {
		this.nroBoleta = nroBoleta;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNomCliente() {
		return nomCliente;
	}

	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

    
}