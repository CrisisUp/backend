// src/main/java/com/mercadoficticio/backend/dto/MovimentacaoFinanceiraRequestDTO.java
package com.mercadoficticio.backend.dto;

import com.mercadoficticio.backend.model.TipoMovimentacao;
import com.mercadoficticio.backend.model.CategoriaMovimentacao; // <<-- NOVO IMPORT
import java.time.LocalDate;

public class MovimentacaoFinanceiraRequestDTO {
    private String descricao;
    private Double valor;
    private TipoMovimentacao tipo;
    private LocalDate data;
    private CategoriaMovimentacao categoria; // <<-- NOVO CAMPO

    // Construtor padrão
    public MovimentacaoFinanceiraRequestDTO() {
    }

    // Construtor com todos os campos (opcional, mas boa prática)
    public MovimentacaoFinanceiraRequestDTO(String descricao, Double valor, TipoMovimentacao tipo, LocalDate data, CategoriaMovimentacao categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
        this.categoria = categoria; // <<-- NOVO PARAMETRO
    }

    // Getters e Setters
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

    // <<-- NOVOS GETTER E SETTER PARA CATEGORIA
    public CategoriaMovimentacao getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaMovimentacao categoria) {
        this.categoria = categoria;
    }
}
