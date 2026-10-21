package com.example.Taller.Service;

import com.example.Taller.dto.PrestamoRequest;
import com.example.Taller.model.Prestamo;
import java.util.List;

public interface PrestamoService {

    Prestamo registrar(PrestamoRequest request);

    List<Prestamo> listar();

    List<Prestamo> listarPorHerramienta(Long herramientaId);
}
