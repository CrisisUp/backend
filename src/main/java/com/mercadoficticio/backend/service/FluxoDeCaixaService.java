// src/main/java/com/mercadoficticio/backend/service/FluxoDeCaixaService.java
package com.mercadoficticio.backend.service;

import com.mercadoficticio.backend.model.MovimentacaoFinanceira;
import com.mercadoficticio.backend.model.TipoMovimentacao;
import com.mercadoficticio.backend.repository.MovimentacaoFinanceiraRepository;
import com.mercadoficticio.backend.dto.MovimentacaoFinanceiraRequestDTO;
import com.mercadoficticio.backend.dto.MovimentacaoFinanceiraResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FluxoDeCaixaService {

    private final MovimentacaoFinanceiraRepository movimentacaoFinanceiraRepository;

    @Autowired
    public FluxoDeCaixaService(MovimentacaoFinanceiraRepository movimentacaoFinanceiraRepository) {
        this.movimentacaoFinanceiraRepository = movimentacaoFinanceiraRepository;
    }

    @Transactional
    public MovimentacaoFinanceiraResponseDTO registrarNovaMovimentacao(MovimentacaoFinanceiraRequestDTO requestDTO) {
        MovimentacaoFinanceira movimentacao = new MovimentacaoFinanceira();
        movimentacao.setDescricao(requestDTO.getDescricao());
        movimentacao.setValor(requestDTO.getValor());
        movimentacao.setTipo(requestDTO.getTipo());

        LocalDate dataMovimentacao = requestDTO.getData();
        if (dataMovimentacao == null) {
            dataMovimentacao = LocalDate.now();
        }
        movimentacao.setData(dataMovimentacao);
        movimentacao.setDataHora(dataMovimentacao.atStartOfDay()); // Define dataHora a partir de data
        movimentacao.setCategoria(requestDTO.getCategoria()); // Define categoria

        MovimentacaoFinanceira movimentacaoSalva = movimentacaoFinanceiraRepository.save(movimentacao);
        return mapearParaResponseDTO(movimentacaoSalva);
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoFinanceiraResponseDTO> listarMovimentacoesPorPeriodo(LocalDate startDate, LocalDate endDate) {
        // CORREÇÃO AQUI: A chamada do método estava correta
        List<MovimentacaoFinanceira> movimentacoes = movimentacaoFinanceiraRepository.findByDataBetween(startDate, endDate);
        return movimentacoes.stream()
                .map(this::mapearParaResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Double calcularSaldoPorPeriodo(LocalDate startDate, LocalDate endDate) {
        // CORREÇÃO AQUI: O método findByDataBetween já existe e estava correto.
        // O erro parece ser uma inconsistência ou digitação no nome do método em MovimentacaoFinanceiraRepository.
        // Vamos usar findByDataBetween, que é o que definimos para período.
        List<MovimentacaoFinanceira> movimentacoes = movimentacaoFinanceiraRepository.findByDataBetween(startDate, endDate);

        double totalReceitas = movimentacoes.stream()
                .filter(m -> m.getTipo() == TipoMovimentacao.RECEITA)
                .mapToDouble(MovimentacaoFinanceira::getValor)
                .sum();

        double totalDespesas = movimentacoes.stream()
                .filter(m -> m.getTipo() == TipoMovimentacao.DESPESA)
                .mapToDouble(MovimentacaoFinanceira::getValor)
                .sum();

        return totalReceitas - totalDespesas;
    }

    private MovimentacaoFinanceiraResponseDTO mapearParaResponseDTO(MovimentacaoFinanceira movimentacao) {
        return new MovimentacaoFinanceiraResponseDTO(
                movimentacao.getId(),
                movimentacao.getDescricao(),
                movimentacao.getValor(),
                movimentacao.getTipo(),
                movimentacao.getData()
        );
    }
}
