package com.mercadoficticio.backend.repository;

import com.mercadoficticio.backend.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui, se necessário.
    // Por exemplo:
    // List<Venda> findByDataVendaBetween(LocalDateTime startDate, LocalDateTime endDate);
}
