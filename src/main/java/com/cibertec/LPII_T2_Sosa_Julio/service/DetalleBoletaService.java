package com.cibertec.LPII_T2_Sosa_Julio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.LPII_T2_Sosa_Julio.model.DetalleBoleta;
import com.cibertec.LPII_T2_Sosa_Julio.model.DetalleBoletaId;
import com.cibertec.LPII_T2_Sosa_Julio.repository.DetalleBoletaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DetalleBoletaService {

    @Autowired
    private DetalleBoletaRepository detalleBoletaRepository;

    public List<DetalleBoleta> listarActivos() {
        return detalleBoletaRepository.findByActivo(0);
    }

    public List<DetalleBoleta> listarPorBoleta(Long nroBoleta) {
        return detalleBoletaRepository.findByBoletaNroBoletaAndActivo(nroBoleta, 0);
    }

    public DetalleBoleta buscar(DetalleBoletaId id) {
        return detalleBoletaRepository.findById(id).orElse(null);
    }

    public void guardar(DetalleBoleta detalle) {
        detalleBoletaRepository.save(detalle);
    }

    public void eliminarLogico(DetalleBoletaId id) {
        DetalleBoleta d = buscar(id);
        if (d != null) {
            d.setActivo(1);
            detalleBoletaRepository.save(d);
        }
    }
}
