package com.example.Taller.Controller;

import com.example.Taller.model.Herramienta;
import com.example.Taller.Service.HerramientaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/herramientas")
public class HerramientaController {

    private final HerramientaService service;

    public HerramientaController(HerramientaService service) { this.service = service; }

    @PostMapping("/Registar")
    public ResponseEntity<Herramienta> registrar(@Valid @RequestBody Herramienta herramienta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(herramienta));
    }

    @GetMapping("/Listar")
    public List<Herramienta> listar() { return service.listar(); }
}