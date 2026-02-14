package com.cibertec.LPII_T2_Sosa_Julio.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class BoletaController {

    @Autowired
    private BoletaService boletaService;
    @Autowired
    private DetalleBoletaService detalleBoletaService;

    @Autowired
    private ProductoService productoService;
    
    
    @GetMapping("/boletas")
    public String listar(Model model) {
    	List<BoletaResumen> resumenes = new ArrayList<>();

        for (Boleta boleta : boletaService.listarActivos()) {
            List<DetalleBoleta> detalles = detalleBoletaService.listarPorBoleta(boleta.getNroBoleta());
            if (detalles.isEmpty()) {
                resumenes.add(new BoletaResumen(boleta.getNroBoleta(), boleta.getFecha(), "-", 0, boleta.getTotal()));
                continue;
            }
            for (DetalleBoleta detalle : detalles) {
                resumenes.add(new BoletaResumen(
                        boleta.getNroBoleta(),
                        boleta.getFecha(),
                        detalle.getProducto().getNomProd(),
                        detalle.getCantidad(),
                        boleta.getTotal()));
            }
        }

        model.addAttribute("boletas", resumenes);
        return "boletas/consultaBoleta";
    }
    @GetMapping("/boletas/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Boleta boleta = boletaService.buscar(id);
        if (boleta == null) {
            ra.addFlashAttribute("error", "La boleta seleccionada no existe.");
            return "redirect:/boletas";
        }

        List<DetalleBoleta> detalles = detalleBoletaService.listarPorBoleta(id);
        DetalleBoleta detalle = detalles.isEmpty() ? new DetalleBoleta() : detalles.get(0);

        model.addAttribute("boleta", boleta);
        model.addAttribute("detalle", detalle);
        model.addAttribute("productos", productoService.listarActivos());
        return "boletas/mantenimientoBoleta";
    }

    @PostMapping("/boletas/actualizar")
    public String actualizar(@RequestParam Long nroBoleta,
                             @RequestParam String fecha,
                             @RequestParam String nomCliente,
                             @RequestParam String estado,
                             @RequestParam Long productoId,
                             @RequestParam Integer cantidad,
                             RedirectAttributes ra) {
        Boleta boleta = boletaService.buscar(nroBoleta);
        Producto producto = productoService.buscar(productoId);

        if (boleta == null || producto == null) {
            ra.addFlashAttribute("error", "No se pudo actualizar la boleta seleccionada.");
            return "redirect:/boletas";
        }

        boleta.setFecha(LocalDate.parse(fecha));
        boleta.setNomCliente(nomCliente);
        boleta.setEstado(estado);
        boleta.setTotal(producto.getPrecio() * cantidad);
        boletaService.guardar(boleta);

        List<DetalleBoleta> detallesActuales = detalleBoletaService.listarPorBoleta(nroBoleta);
        for (DetalleBoleta detalleActual : detallesActuales) {
            detalleActual.setActivo(1);
            detalleBoletaService.guardar(detalleActual);
        }

        DetalleBoleta detalleNuevo = new DetalleBoleta();
        detalleNuevo.setId(new DetalleBoletaId(nroBoleta, productoId));
        detalleNuevo.setBoleta(boleta);
        detalleNuevo.setProducto(producto);
        detalleNuevo.setCantidad(cantidad);
        detalleNuevo.setActivo(0);
        detalleBoletaService.guardar(detalleNuevo);

        ra.addFlashAttribute("success", "Boleta actualizada correctamente.");
        return "redirect:/boletas";
    }
    @GetMapping("/boletas/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("boleta", new Boleta());
        model.addAttribute("productos", productoService.listarActivos());
        return "boletas/formulario";
    }

    @PostMapping("/boletas/guardar")
    public String guardar(@RequestParam String fecha,
            @RequestParam String nomCliente,
            @RequestParam String estado,
            @RequestParam Long productoId,
            @RequestParam Integer cantidad,
            RedirectAttributes ra) {
Producto producto = productoService.buscar(productoId);

if (producto == null) {
ra.addFlashAttribute("error", "No se pudo registrar la boleta porque el producto no existe.");
return "redirect:/boletas";
}

Boleta boleta = new Boleta();
boleta.setFecha(LocalDate.parse(fecha));
boleta.setNomCliente(nomCliente);
boleta.setEstado(estado);
boleta.setTotal(producto.getPrecio() * cantidad);
boleta.setActivo(0);
boletaService.guardar(boleta);

DetalleBoleta detalle = new DetalleBoleta();
detalle.setId(new DetalleBoletaId(boleta.getNroBoleta(), productoId));
detalle.setBoleta(boleta);
detalle.setProducto(producto);
detalle.setCantidad(cantidad);
detalle.setActivo(0);
detalleBoletaService.guardar(detalle);

ra.addFlashAttribute("success", "Boleta registrada correctamente.");
return "redirect:/boletas";
    }
    public static class BoletaResumen {
        private final Long nroBoleta;
        private final LocalDate fecha;
        private final String producto;
        private final Integer cantidad;
        private final Double total;

        public BoletaResumen(Long nroBoleta, LocalDate fecha, String producto, Integer cantidad, Double total) {
            this.nroBoleta = nroBoleta;
            this.fecha = fecha;
            this.producto = producto;
            this.cantidad = cantidad;
            this.total = total;
        }

        public Long getNroBoleta() {
            return nroBoleta;
        }

        public LocalDate getFecha() {
            return fecha;
        }

        public String getProducto() {
            return producto;
        }

        public Integer getCantidad() {
            return cantidad;
        }

        public Double getTotal() {
            return total;
        }
    }
}
