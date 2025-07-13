// src/main/java/com/mercadoficticio/backend/dto/MovimentacaoFinanceiraResponseDTO.java
package com.mercadoficticio.backend.dto;

import com.mercadoficticio.backend.model.TipoMovimentacao;
import java.time.LocalDate;

public class MovimentacaoFinanceiraResponseDTO {
    private Long id;
    private String descricao;
    private Double valor;
    private TipoMovimentacao tipo;
    private LocalDate data;

    // Construtor padrão
    public MovimentacaoFinanceiraResponseDTO() {
    }

    // Construtor com todos os campos
    public MovimentacaoFinanceiraResponseDTO(Long id, String descricao, Double valor, TipoMovimentacao tipo, LocalDate data) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}