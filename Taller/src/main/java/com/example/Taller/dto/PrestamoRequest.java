package com.example.Taller.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class PrestamoRequest {

    @NotNull(message = "La herramienta es obligatoria")
    private Long herramientaId;

    @NotBlank(message = "El responsable es obligatorio")
    private String responsable;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fechaPrestamo;

    @Positive(message = "La cantidad debe ser mayor que cero")
    private int cantidad;

    public PrestamoRequest() {}

    public Long getHerramientaId() { return herramientaId; }
    public void setHerramientaId(Long herramientaId) { this.herramientaId = herramientaId; }
    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }
    public LocalDate getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(LocalDate fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}