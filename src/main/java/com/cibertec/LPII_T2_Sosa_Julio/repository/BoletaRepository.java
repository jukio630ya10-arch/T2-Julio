package com.cibertec.LPII_T2_Sosa_Julio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.LPII_T2_Sosa_Julio.model.Boleta;
public interface BoletaRepository extends JpaRepository<Boleta, Long> {

    List<Boleta> findByActivo(Integer activo);
}
