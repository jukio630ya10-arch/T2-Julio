package com.cibertec.LPII_T2_Sosa_Julio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.LPII_T2_Sosa_Julio.model.DetalleBoleta;
import com.cibertec.LPII_T2_Sosa_Julio.service.BoletaService;
import com.cibertec.LPII_T2_Sosa_Julio.service.DetalleBoletaService;
import com.cibertec.LPII_T2_Sosa_Julio.service.ProductoService;

@Controller
public class DetalleBoletaController {

    @Autowired
    private DetalleBoletaService detalleService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private BoletaService boletaService;

    @GetMapping("/detalle/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("detalle", new DetalleBoleta());
        model.addAttribute("productos", productoService.listarActivos());
        model.addAttribute("boletas", boletaService.listarActivos());
        return "detalle/formulario";
    }

    @PostMapping("/detalle/guardar")
    public String guardar(DetalleBoleta detalle) {
        detalleService.guardar(detalle);
        return "redirect:/boletas";
    }
}

