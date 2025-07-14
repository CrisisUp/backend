// src/main/java/com/mercadoficticio/backend/controller/FluxoDeCaixaController.java
package com.mercadoficticio.backend.controller;

import com.mercadoficticio.backend.dto.MovimentacaoFinanceiraRequestDTO;
import com.mercadoficticio.backend.dto.MovimentacaoFinanceiraResponseDTO;
import com.mercadoficticio.backend.service.FluxoDeCaixaService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin; // Importe esta linha se ainda não estiver

import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/fluxo-de-caixa")
@CrossOrigin(origins = "http://localhost:4200") // Confirme que esta linha está presente
public class FluxoDeCaixaController {
    private static final Logger log = LoggerFactory.getLogger(FluxoDeCaixaController.class);
    private final FluxoDeCaixaService fluxoDeCaixaService;

    @Autowired
    public FluxoDeCaixaController(FluxoDeCaixaService fluxoDeCaixaService) {
        this.fluxoDeCaixaService = fluxoDeCaixaService;
    }

    @PostMapping("/movimentacoes")
    public ResponseEntity<MovimentacaoFinanceiraResponseDTO> registrarMovimentacao(@RequestBody MovimentacaoFinanceiraRequestDTO requestDTO) {
        try {
            MovimentacaoFinanceiraResponseDTO novaMovimentacao = fluxoDeCaixaService.registrarNovaMovimentacao(requestDTO);
            return new ResponseEntity<>(novaMovimentacao, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Erro ao registrar movimentação: {}", requestDTO, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para listar movimentações por período (GET /api/fluxo-de-caixa/movimentacoes)
    @GetMapping("/movimentacoes")
    public ResponseEntity<List<MovimentacaoFinanceiraResponseDTO>> listarMovimentacoes(
            @RequestParam(value = "dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio, // <-- ADICIONE (value = "dataInicio")
            @RequestParam(value = "dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {      // <-- ADICIONE (value = "dataFim")

        List<MovimentacaoFinanceiraResponseDTO> movimentacoes = fluxoDeCaixaService.listarMovimentacoesPorPeriodo(dataInicio, dataFim);
        return new ResponseEntity<>(movimentacoes, HttpStatus.OK);
    }

    // Endpoint para calcular o saldo por período (GET /api/fluxo-de-caixa/saldo)
    @GetMapping("/saldo")
    public ResponseEntity<Double> calcularSaldo(
            @RequestParam(value = "dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio, // <-- ADICIONE (value = "dataInicio")
            @RequestParam(value = "dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {      // <-- ADICIONE (value = "dataFim")

        Double saldo = fluxoDeCaixaService.calcularSaldoPorPeriodo(dataInicio, dataFim);
        return new ResponseEntity<>(saldo, HttpStatus.OK);
    }
}
