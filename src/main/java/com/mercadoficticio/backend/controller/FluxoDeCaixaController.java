// src/main/java/com/mercadoficticio/backend/controller/FluxoDeCaixaController.java
package com.mercadoficticio.backend.controller;

import com.mercadoficticio.backend.dto.MovimentacaoFinanceiraRequestDTO;
import com.mercadoficticio.backend.dto.MovimentacaoFinanceiraResponseDTO;
import com.mercadoficticio.backend.service.FluxoDeCaixaService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat; // Para formatar datas na URL
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/fluxo-de-caixa") // Define o caminho base para os endpoints de fluxo de caixa
public class FluxoDeCaixaController {
    private static final Logger log = LoggerFactory.getLogger(FluxoDeCaixaController.class);
    private final FluxoDeCaixaService fluxoDeCaixaService;

    @Autowired
    public FluxoDeCaixaController(FluxoDeCaixaService fluxoDeCaixaService) {
        this.fluxoDeCaixaService = fluxoDeCaixaService;
    }

    // Endpoint para registrar uma nova movimentação financeira (POST /api/fluxo-de-caixa/movimentacoes)
    @PostMapping("/movimentacoes")
    public ResponseEntity<MovimentacaoFinanceiraResponseDTO> registrarMovimentacao(@RequestBody MovimentacaoFinanceiraRequestDTO requestDTO) {
        try {
            MovimentacaoFinanceiraResponseDTO novaMovimentacao = fluxoDeCaixaService.registrarNovaMovimentacao(requestDTO);
            return new ResponseEntity<>(novaMovimentacao, HttpStatus.CREATED); // Retorna 201 Created
        } catch (Exception e) {
            // Outros erros internos do servidor
            log.error("Erro ao registrar movimentação: {}", requestDTO, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500 Internal Server Error
        }
    }

    // Endpoint para listar movimentações por período (GET /api/fluxo-de-caixa/movimentacoes)
    // Ex: /api/fluxo-de-caixa/movimentacoes?dataInicio=2025-01-01&dataFim=2025-12-31
    @GetMapping("/movimentacoes")
    public ResponseEntity<List<MovimentacaoFinanceiraResponseDTO>> listarMovimentacoes(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        List<MovimentacaoFinanceiraResponseDTO> movimentacoes = fluxoDeCaixaService.listarMovimentacoesPorPeriodo(dataInicio, dataFim);
        return new ResponseEntity<>(movimentacoes, HttpStatus.OK); // Retorna 200 OK
    }

    // Endpoint para calcular o saldo por período (GET /api/fluxo-de-caixa/saldo)
    // Ex: /api/fluxo-de-caixa/saldo?dataInicio=2025-01-01&dataFim=2025-12-31
    @GetMapping("/saldo")
    public ResponseEntity<Double> calcularSaldo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        Double saldo = fluxoDeCaixaService.calcularSaldoPorPeriodo(dataInicio, dataFim);
        return new ResponseEntity<>(saldo, HttpStatus.OK); // Retorna 200 OK
    }
}
