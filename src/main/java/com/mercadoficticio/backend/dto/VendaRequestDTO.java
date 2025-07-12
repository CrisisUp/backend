// src/main/java/com/mercadoficticio/backend/dto/VendaRequestDTO.java
package com.mercadoficticio.backend.dto;

import java.util.List;

public class VendaRequestDTO {
    private List<ItemVendaRequestDTO> itens;

    // Construtor padrão
    public VendaRequestDTO() {
    }

    // Construtor com todos os argumentos
    public VendaRequestDTO(List<ItemVendaRequestDTO> itens) {
        this.itens = itens;
    }

    // Getters e Setters
    public List<ItemVendaRequestDTO> getItens() { return itens; }
    public void setItens(List<ItemVendaRequestDTO> itens) { this.itens = itens; }
}