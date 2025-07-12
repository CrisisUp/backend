// src/main/java/com/mercadoficticio/backend/model/ItemVenda.java
package com.mercadoficticio.backend.model;

import jakarta.persistence.*;

@Entity
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double precoUnitarioVenda; // Preço pelo qual o produto foi vendido

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    // Construtor padrão (obrigatório para JPA)
    public ItemVenda() {
    }

    // Construtor com parâmetros
    public ItemVenda(Produto produto, Integer quantidade, Double precoUnitarioVenda, Venda venda) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitarioVenda = precoUnitarioVenda;
        this.venda = venda;
    }

    // Método para calcular o subtotal deste item de venda
    public Double getSubtotal() {
        return precoUnitarioVenda * quantidade;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Double getPrecoUnitarioVenda() { return precoUnitarioVenda; }
    public void setPrecoUnitarioVenda(Double precoUnitarioVenda) { this.precoUnitarioVenda = precoUnitarioVenda; }

    public Venda getVenda() { return venda; }
    public void setVenda(Venda venda) { this.venda = venda; }
}
