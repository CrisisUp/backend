// src/main/java/com/mercadoficticio/backend/dto/ItemCompraResponseDTO.java
package com.mercadoficticio.backend.dto;

public class ItemCompraResponseDTO {
    private Long id;
    private Long produtoId;
    private String nomeProduto; // Nome do produto para exibição
    private Integer quantidade;
    private Double precoUnitarioCusto;
    private Double subtotal; // Subtotal do item

    // Construtor padrão
    public ItemCompraResponseDTO() {
    }

    // Construtor com argumentos
    public ItemCompraResponseDTO(Long id, Long produtoId, String nomeProduto, Integer quantidade, Double precoUnitarioCusto, Double subtotal) {
        this.id = id;
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.precoUnitarioCusto = precoUnitarioCusto;
        this.subtotal = subtotal;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
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

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
