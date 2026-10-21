package com.example.Taller.Service.ServiceImp;

import com.example.Taller.dto.PrestamoRequest;
import com.example.Taller.exception.RecursoNoEncontradoException;
import com.example.Taller.exception.ReglaNegocioException;
import com.example.Taller.model.Herramienta;
import com.example.Taller.model.Prestamo;
import com.example.Taller.Repository.HerramientaRepository;
import com.example.Taller.Repository.PrestamoRepository;
import com.example.Taller.Service.PrestamoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final HerramientaRepository herramientaRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository,
                               HerramientaRepository herramientaRepository) {
        this.prestamoRepository = prestamoRepository;
        this.herramientaRepository = herramientaRepository;
    }

    @Override
    @Transactional
    public Prestamo registrar(PrestamoRequest request) {
        Herramienta herramienta = herramientaRepository.findById(request.getHerramientaId())
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "La herramienta con id " + request.getHerramientaId() + " no existe"));

        if (request.getCantidad() <= 0) {
            throw new ReglaNegocioException("La cantidad debe ser mayor que cero");
        }
        if (request.getCantidad() > herramienta.getCantidad()) {
            throw new ReglaNegocioException("Unidades insuficientes. Disponibles: "
                    + herramienta.getCantidad());
        }

        // Descuento de inventario + registro del préstamo en la MISMA transacción
        herramienta.setCantidad(herramienta.getCantidad() - request.getCantidad());
        herramientaRepository.save(herramienta);

        Prestamo prestamo = new Prestamo();
        prestamo.setHerramienta(herramienta);
        prestamo.setResponsable(request.getResponsable());
        prestamo.setFechaPrestamo(request.getFechaPrestamo());
        prestamo.setCantidad(request.getCantidad());

        return prestamoRepository.save(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Prestamo> listar() {
        return prestamoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Prestamo> listarPorHerramienta(Long herramientaId) {
        if (!herramientaRepository.existsById(herramientaId)) {
            throw new RecursoNoEncontradoException(
                    "La herramienta con id " + herramientaId + " no existe");
        }
        return prestamoRepository.findByHerramientaId(herramientaId);
    }
}