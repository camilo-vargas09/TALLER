package com.example.Taller.Repository;

import com.example.Taller.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByHerramientaId(Long herramientaId);
}
