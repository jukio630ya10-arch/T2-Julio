package com.cibertec.LPII_T2_Sosa_Julio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.LPII_T2_Sosa_Julio.model.Producto;
import com.cibertec.LPII_T2_Sosa_Julio.repository.ProductoRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarActivos() {
        return productoRepository.findByActivo(0);
    }

    public Producto buscar(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

    public void eliminarLogico(Long id) {
        Producto p = buscar(id);
        if (p != null) {
            p.setActivo(1);
            productoRepository.save(p);
        }
    }
}
