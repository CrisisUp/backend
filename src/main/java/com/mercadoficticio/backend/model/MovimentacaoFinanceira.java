// src/main/java/com/mercadoficticio/backend/model/MovimentacaoFinanceira.java
package com.mercadoficticio.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
public class MovimentacaoFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Double valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao tipo;

    @Column(nullable = false)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaMovimentacao categoria;

    // Construtor padrão (SEM ARGUMENTOS) - OBRIGATÓRIO PARA JPA
    public MovimentacaoFinanceira() {
    }

    // Construtor com parâmetros (útil para criar objetos)
    public MovimentacaoFinanceira(String descricao, Double valor, TipoMovimentacao tipo, CategoriaMovimentacao categoria) {
        this.dataHora = LocalDateTime.now(); // Define a data/hora atual por padrão
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.data = data;
        this.categoria = categoria;
    }

    // --- GETTERS E SETTERS MANUAIS ---
    // Você precisa ter getters e setters para TODOS os campos!
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public TipoMovimentacao getTipo() { return tipo; }
    public void setTipo(TipoMovimentacao tipo) { this.tipo = tipo; }

    public LocalDate getData() { return data; } // <<-- Corrigido para retornar 'this.data'
    public void setData(LocalDate data) { this.data = data; }

    public CategoriaMovimentacao getCategoria() { return categoria; }
    public void setCategoria(CategoriaMovimentacao categoria) { this.categoria = categoria; }
}