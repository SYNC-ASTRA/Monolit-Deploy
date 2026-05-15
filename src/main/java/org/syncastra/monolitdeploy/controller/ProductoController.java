package org.syncastra.monolitdeploy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.syncastra.monolitdeploy.model.Producto;
import org.syncastra.monolitdeploy.repository.ProductoRepository;

@Controller
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/productos";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/productos")
    public String listar(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "productos-list";
    }

    @GetMapping("/productos/nuevo")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto-form";
    }

    @PostMapping("/productos/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoRepository.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de producto inválido:" + id));
        model.addAttribute("producto", producto);
        return "producto-form";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID de producto inválido:" + id));
        productoRepository.delete(producto);
        return "redirect:/productos";
    }
}
