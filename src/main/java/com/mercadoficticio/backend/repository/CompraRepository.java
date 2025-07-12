// src/main/java/com/mercadoficticio/backend/repository/CompraRepository.java
package com.mercadoficticio.backend.repository;

import com.mercadoficticio.backend.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime; // Importe LocalDateTime
import java.util.List; // Importe List

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    // Buscar compras por ID do fornecedor
    List<Compra> findByFornecedorId(Long fornecedorId);

    // Buscar compras em um período de tempo (entre duas datas/horas)
    List<Compra> findByDataCompraBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
