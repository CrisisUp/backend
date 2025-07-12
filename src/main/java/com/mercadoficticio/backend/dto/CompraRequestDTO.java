// src/main/java/com/mercadoficticio/backend/dto/CompraRequestDTO.java
package com.mercadoficticio.backend.dto;

import java.util.List;

public class CompraRequestDTO {
    private Long fornecedorId;
    private List<ItemCompraRequestDTO> itens;

    // Construtor padrão
    public CompraRequestDTO() {
    }

    // Construtor com todos os argumentos
    public CompraRequestDTO(Long fornecedorId, List<ItemCompraRequestDTO> itens) {
        this.fornecedorId = fornecedorId;
        this.itens = itens;
    }

    // Getters e Setters
    public Long getFornecedorId() {
        return fornecedorId;
    }

    public void setFornecedorId(Long fornecedorId) {
        this.fornecedorId = fornecedorId;
    }

    public List<ItemCompraRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompraRequestDTO> itens) {
        this.itens = itens;
    }
}