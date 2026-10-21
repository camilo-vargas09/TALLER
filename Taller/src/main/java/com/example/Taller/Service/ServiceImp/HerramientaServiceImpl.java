package com.example.Taller.Service.ServiceImp;

import com.example.Taller.exception.ReglaNegocioException;
import com.example.Taller.model.Herramienta;
import com.example.Taller.Repository.HerramientaRepository;
import com.example.Taller.Service.HerramientaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HerramientaServiceImpl implements HerramientaService {

    private final HerramientaRepository herramientaRepository;

    public HerramientaServiceImpl(HerramientaRepository herramientaRepository) {
        this.herramientaRepository = herramientaRepository;
    }

    @Override
    @Transactional
    public Herramienta registrar(Herramienta herramienta) {
        if (herramienta.getCantidad() < 0) {
            throw new ReglaNegocioException("La cantidad no puede ser negativa");
        }
        herramienta.setId(null); // evita sobrescribir una herramienta existente
        return herramientaRepository.save(herramienta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Herramienta> listar() {
        return herramientaRepository.findAll();
    }
}
