package com.cibertec.LPII_T2_Sosa_Julio.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.LPII_T2_Sosa_Julio.model.Boleta;
import com.cibertec.LPII_T2_Sosa_Julio.service.BoletaService;

@Controller
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    @GetMapping("/boletas")
    public String listar(Model model) {
        model.addAttribute("boletas", boletaService.listarActivos());
        return "boletas/lista";
    }

    @GetMapping("/boletas/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("boleta", new Boleta());
        return "boletas/formulario";
    }

    @PostMapping("/boletas/guardar")
    public String guardar(Boleta boleta) {
        boleta.setFecha(LocalDate.now());
        boletaService.guardar(boleta);
        return "redirect:/boletas";
    }

    @GetMapping("/boletas/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("boleta", boletaService.buscar(id));
        return "boletas/formulario";
    }

    @GetMapping("/boletas/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        boletaService.eliminarLogico(id);
        return "redirect:/boletas";
    }
}
