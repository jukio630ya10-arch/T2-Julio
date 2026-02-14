package com.cibertec.LPII_T2_Sosa_Julio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.LPII_T2_Sosa_Julio.model.Boleta;
import com.cibertec.LPII_T2_Sosa_Julio.model.DetalleBoleta;
import com.cibertec.LPII_T2_Sosa_Julio.model.DetalleBoletaId;
import com.cibertec.LPII_T2_Sosa_Julio.model.Producto;
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
    public String guardar(@RequestParam Long nroBoleta,
                          @RequestParam Long productoId,
                          @RequestParam Integer cantidad,
                          RedirectAttributes ra) {
        Boleta boleta = boletaService.buscar(nroBoleta);
        Producto producto = productoService.buscar(productoId);

        if (boleta == null || producto == null || cantidad == null || cantidad <= 0) {
            ra.addFlashAttribute("error", "Datos inválidos para registrar detalle.");
            return "redirect:/detalle/nuevo";
        }

        DetalleBoleta detalle = new DetalleBoleta();
        detalle.setId(new DetalleBoletaId(nroBoleta, productoId));
        detalle.setBoleta(boleta);
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalle.setActivo(0);

        detalleService.guardar(detalle);
        ra.addFlashAttribute("success", "Detalle registrado correctamente.");
        return "redirect:/boletas";
    }

}

