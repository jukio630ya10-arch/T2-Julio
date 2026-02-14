package com.cibertec.LPII_T2_Sosa_Julio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.LPII_T2_Sosa_Julio.model.Producto;
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByActivo(Integer activo);
}


