// src/main/java/com/mercadoficticio/backend/dto/ItemCompraRequestDTO.java
package com.mercadoficticio.backend.dto;

public class ItemCompraRequestDTO {
    private Long produtoId;
    private Integer quantidade;
    private Double precoUnitarioCusto;

    // Construtor padrão
    public ItemCompraRequestDTO() {
    }

    // Construtor com todos os argumentos
    public ItemCompraRequestDTO(Long produtoId, Integer quantidade, Double precoUnitarioCusto) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoUnitarioCusto = precoUnitarioCusto;
    }

    // Getters e Setters
    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitarioCusto() {
        return precoUnitarioCusto;
    }

    public void setPrecoUnitarioCusto(Double precoUnitarioCusto) {
        this.precoUnitarioCusto = precoUnitarioCusto;
    }
}