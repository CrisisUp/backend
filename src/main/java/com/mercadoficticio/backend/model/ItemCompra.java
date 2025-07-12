// src/main/java/com/mercadoficticio/backend/model/ItemCompra.java
package com.mercadoficticio.backend.model;

import jakarta.persistence.*;
// REMOVA import lombok.Data;
// REMOVA import lombok.NoArgsConstructor;
// REMOVA import lombok.AllArgsConstructor;

@Entity
// REMOVA @Data
// REMOVA @NoArgsConstructor
// REMOVA @AllArgsConstructor
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double precoUnitarioCusto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    // Construtor padrão (obrigatório para JPA)
    public ItemCompra() {
    }

    // Construtor com todos os campos (útil)
    public ItemCompra(Produto produto, Integer quantidade, Double precoUnitarioCusto, Compra compra) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitarioCusto = precoUnitarioCusto;
        this.compra = compra;
    }

    // --- GETTERS E SETTERS MANUAIS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    // Método para calcular o subtotal deste item
    public Double getSubtotal() {
        return precoUnitarioCusto * quantidade;
    }
}