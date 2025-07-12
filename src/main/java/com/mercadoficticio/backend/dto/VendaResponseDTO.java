// src/main/java/com/mercadoficticio/backend/dto/VendaResponseDTO.java
package com.mercadoficticio.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class VendaResponseDTO {
    private Long id;
    private LocalDateTime dataVenda;
    private Double valorTotal;
    private List<ItemVendaResponseDTO> itens;

    // Construtor padrão
    public VendaResponseDTO() {
    }

    // Construtor com todos os argumentos
    public VendaResponseDTO(Long id, LocalDateTime dataVenda, Double valorTotal, List<ItemVendaResponseDTO> itens) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
        this.itens = itens;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataVenda() { return dataVenda; }
    public void setDataVenda(LocalDateTime dataVenda) { this.dataVenda = dataVenda; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public List<ItemVendaResponseDTO> getItens() { return itens; }
    public void setItens(List<ItemVendaResponseDTO> itens) { this.itens = itens; }
}
