package com.cibertec.LPII_T2_Sosa_Julio.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class DetalleBoletaId implements Serializable {

    private Long nroBoleta;
    private Long idProd;

    // Constructor vacío (obligatorio)
    public DetalleBoletaId() {
    }

    // Constructor con parámetros
    public DetalleBoletaId(Long nroBoleta, Long idProd) {
        this.nroBoleta = nroBoleta;
        this.idProd = idProd;
    }

    // Getters y Setters
    public Long getNroBoleta() {
        return nroBoleta;
    }

    public void setNroBoleta(Long nroBoleta) {
        this.nroBoleta = nroBoleta;
    }

    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetalleBoletaId)) return false;
        DetalleBoletaId that = (DetalleBoletaId) o;
        return Objects.equals(nroBoleta, that.nroBoleta) &&
               Objects.equals(idProd, that.idProd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nroBoleta, idProd);
    }
}
