// src/main/java/com/mercadoficticio/backend/dto/ItemVendaResponseDTO.java
package com.mercadoficticio.backend.dto;

public class ItemVendaResponseDTO {
    private Long id;
    private Long produtoId;
    private String nomeProduto; // Nome do produto para exibição
    private Integer quantidade;
    private Double precoUnitarioVenda;
    private Double subtotal; // Subtotal do item

    // Construtor padrão
    public ItemVendaResponseDTO() {
    }

    // Construtor com todos os argumentos
    public ItemVendaResponseDTO(Long id, Long produtoId, String nomeProduto, Integer quantidade, Double precoUnitarioVenda, Double subtotal) {
        this.id = id;
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnitarioVenda = precoUnitarioVenda;
        this.subtotal = subtotal;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProdutoId() { return produtoId; }
    public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Double getPrecoUnitarioVenda() { return precoUnitarioVenda; }
    public void setPrecoUnitarioVenda(Double precoUnitarioVenda) { this.precoUnitarioVenda = precoUnitarioVenda; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
}
