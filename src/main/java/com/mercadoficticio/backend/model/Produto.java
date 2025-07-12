// src/main/java/com/mercadoficticio/backend/model/Produto.java
package com.mercadoficticio.backend.model;

import jakarta.persistence.*; // IMPORTE ISSO!

@Entity // <--- ESTA ANOTAÇÃO É OBRIGATÓRIA E DEVE ESTAR AQUI!
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Column(nullable = false)
    private Double precoCusto;

    @Column(nullable = false)
    private Double precoVenda;

    @Column(nullable = false)
    private Integer quantidadeEstoque;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    // Construtor padrão (SEM ARGUMENTOS) - OBRIGATÓRIO PARA JPA
    public Produto() {
    }

    // Construtor com todos os campos (útil)
    public Produto(String nome, String descricao, Double precoCusto, Double precoVenda, Integer quantidadeEstoque, Fornecedor fornecedor) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
        this.fornecedor = fornecedor;
    }

    // --- GETTERS E SETTERS MANUAIS ---
    // Você precisa ter getters e setters para TODOS os campos!
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getPrecoCusto() { return precoCusto; }
    public void setPrecoCusto(Double precoCusto) { this.precoCusto = precoCusto; }

    public Double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(Double precoVenda) { this.precoVenda = precoVenda; }

    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }

    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }


    // Métodos de lógica de negócio (se você os manteve)
    public Double calcularMargemLucro() {
        if (precoCusto == null || precoCusto <= 0) {
            return 0.0;
        }
        return ((precoVenda - precoCusto) / precoCusto) * 100;
    }

    public Double calcularLucroPorUnidade() {
        if (precoCusto == null || precoVenda == null) {
            return 0.0;
        }
        return precoVenda - precoCusto;
    }
}