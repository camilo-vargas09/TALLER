package com.example.Taller.Service;

import com.example.Taller.model.Herramienta;
import java.util.List;

public interface HerramientaService {

    Herramienta registrar(Herramienta herramienta);

    List<Herramienta> listar();
}