// src/main/java/com/mercadoficticio/backend/dto/ProdutoResponseDTO.java
package com.mercadoficticio.backend.dto;

// REMOVA import lombok.Data;
// REMOVA import lombok.NoArgsConstructor;
// REMOVA import lombok.AllArgsConstructor;

public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double precoCusto;
    private Double precoVenda;
    private Integer quantidadeEstoque;
    private Double margemLucro;
    private Long fornecedorId;
    private String nomeFornecedor;

    // Construtor padrão
    public ProdutoResponseDTO() {
    }

    // Construtor com todos os argumentos
    public ProdutoResponseDTO(Long id, String nome, String descricao, Double precoCusto, Double precoVenda, Integer quantidadeEstoque, Double margemLucro, Long fornecedorId, String nomeFornecedor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
        this.margemLucro = margemLucro;
        this.fornecedorId = fornecedorId;
        this.nomeFornecedor = nomeFornecedor;
    }

    // --- GETTERS E SETTERS MANUAIS ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }
}
