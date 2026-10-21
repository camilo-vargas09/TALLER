package com.example.Taller.Controller;

import com.example.Taller.dto.PrestamoRequest;
import com.example.Taller.model.Prestamo;
import com.example.Taller.Service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService service;

    public PrestamoController(PrestamoService service) { this.service = service; }

    @PostMapping("/Registrar")
    public ResponseEntity<Prestamo> registrar(@Valid @RequestBody PrestamoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(request));
    }

    @GetMapping("/listar")
    public List<Prestamo> listar() { return service.listar(); }

    @GetMapping("/herramienta/{id}")
    public List<Prestamo> listarPorHerramienta(@PathVariable Long id) {
        return service.listarPorHerramienta(id);
    }
}