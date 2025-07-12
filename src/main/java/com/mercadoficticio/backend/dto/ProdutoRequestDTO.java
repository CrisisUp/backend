// src/main/java/com/mercadoficticio/backend/dto/ProdutoRequestDTO.java
package com.mercadoficticio.backend.dto;

// REMOVA import lombok.Data;
// REMOVA import lombok.NoArgsConstructor;
// REMOVA import lombok.AllArgsConstructor;

public class ProdutoRequestDTO {
    private String nome;
    private String descricao;
    private Double precoCusto;
    private Double precoVenda;
    private Integer quantidadeEstoque;
    private Long fornecedorId;

    // Construtor padrão
    public ProdutoRequestDTO() {
    }

    // Construtor com todos os argumentos
    public ProdutoRequestDTO(String nome, String descricao, Double precoCusto, Double precoVenda, Integer quantidadeEstoque, Long fornecedorId) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
        this.fornecedorId = fornecedorId;
    }

    // --- GETTERS E SETTERS MANUAIS ---

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }
}
