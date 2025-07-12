// src/main/java/com/mercadoficticio/backend/dto/CompraResponseDTO.java
package com.mercadoficticio.backend.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.mercadoficticio.backend.model.Compra;
import com.mercadoficticio.backend.model.ItemCompra;

public class CompraResponseDTO {
    private Long id;
    private LocalDateTime dataCompra;
    private Double valorTotal;
    private Long fornecedorId;
    private String nomeFornecedor;
    private List<ItemCompraResponseDTO> itens;

    // Construtor padrão
    public CompraResponseDTO() {
    }

    // Construtor com argumentos
    public CompraResponseDTO(Long id, LocalDateTime dataCompra, Double valorTotal, Long fornecedorId, String nomeFornecedor, List<ItemCompraResponseDTO> itens) {
        this.id = id;
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
        this.fornecedorId = fornecedorId;
        this.nomeFornecedor = nomeFornecedor;
        this.itens = itens;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
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

    public List<ItemCompraResponseDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompraResponseDTO> itens) {
        this.itens = itens;
    }
}
