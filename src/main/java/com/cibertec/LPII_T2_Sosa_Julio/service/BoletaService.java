package com.cibertec.LPII_T2_Sosa_Julio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.LPII_T2_Sosa_Julio.model.Boleta;
import com.cibertec.LPII_T2_Sosa_Julio.repository.BoletaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    public List<Boleta> listarActivos() {
        return boletaRepository.findByActivo(0);
    }

    public Boleta buscar(Long id) {
        return boletaRepository.findById(id).orElse(null);
    }

    public void guardar(Boleta boleta) {
        boletaRepository.save(boleta);
    }

    public void eliminarLogico(Long id) {
        Boleta b = buscar(id);
        if (b != null) {
            b.setActivo(1);
            boletaRepository.save(b);
        }
    }
}
