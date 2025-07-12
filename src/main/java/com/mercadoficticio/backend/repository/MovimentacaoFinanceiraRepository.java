// src/main/java/com/mercadoficticio/backend/repository/MovimentacaoFinanceiraRepository.java
package com.mercadoficticio.backend.repository;

import com.mercadoficticio.backend.model.CategoriaMovimentacao;
import com.mercadoficticio.backend.model.MovimentacaoFinanceira;
import com.mercadoficticio.backend.model.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime; // Importe LocalDateTime
import java.util.List; // Importe List

@Repository
public interface MovimentacaoFinanceiraRepository extends JpaRepository<MovimentacaoFinanceira, Long> {

    // Buscar movimentações por tipo (RECEITA ou DESPESA)
    List<MovimentacaoFinanceira> findByTipo(TipoMovimentacao tipo);

    // Buscar movimentações por categoria
    List<MovimentacaoFinanceira> findByCategoria(CategoriaMovimentacao categoria);

    // Buscar movimentações em um período de tempo
    List<MovimentacaoFinanceira> findByDataHoraBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

    // Buscar movimentações por tipo e categoria
    List<MovimentacaoFinanceira> findByTipoAndCategoria(TipoMovimentacao tipo, CategoriaMovimentacao categoria);
}