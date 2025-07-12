// src/main/java/com/mercadoficticio/backend/dto/ItemVendaRequestDTO.java
package com.mercadoficticio.backend.dto;

public class ItemVendaRequestDTO {
    private Long produtoId;
    private Integer quantidade;
    private Double precoUnitarioVenda;

    // Construtor padrão
    public ItemVendaRequestDTO() {
    }

    // Construtor com todos os argumentos
    public ItemVendaRequestDTO(Long produtoId, Integer quantidade, Double precoUnitarioVenda) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoUnitarioVenda = precoUnitarioVenda;
    }

    // Getters e Setters
    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Double getPrecoUnitarioVenda() { return precoUnitarioVenda; }
    public void setPrecoUnitarioVenda(Double precoUnitarioVenda) { this.precoUnitarioVenda = precoUnitarioVenda; }
}