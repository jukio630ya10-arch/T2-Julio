package com.cibertec.LPII_T2_Sosa_Julio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.LPII_T2_Sosa_Julio.model.DetalleBoleta;
import com.cibertec.LPII_T2_Sosa_Julio.model.DetalleBoletaId;

@Repository
public interface DetalleBoletaRepository 
extends JpaRepository<DetalleBoleta, DetalleBoletaId> {

List<DetalleBoleta> findByActivo(Integer activo);
}
