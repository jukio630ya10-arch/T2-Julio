package com.cibertec.LPII_T2_Sosa_Julio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.LPII_T2_Sosa_Julio.model.Producto;
import com.cibertec.LPII_T2_Sosa_Julio.service.ProductoService;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/productos")
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listarActivos());
        return "productos/lista";
    }

    @GetMapping("/productos/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/formulario";
    }

    @PostMapping("/productos/guardar")
    public String guardar(Producto producto) {
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("producto", productoService.buscar(id));
        return "productos/formulario";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        productoService.eliminarLogico(id);
        return "redirect:/productos";
    }
}
