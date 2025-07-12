// src/main/java/com/mercadoficticio/backend/model/Venda.java
package com.mercadoficticio.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataVenda;

    @Column(nullable = false)
    private Double valorTotal;

    // Relacionamento um para muitos: uma venda tem muitos itens
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemVenda> itens = new ArrayList<>();

    // Construtor padrão (obrigatório para JPA)
    public Venda() {
        this.dataVenda = LocalDateTime.now(); // Define a data/hora atual por padrão
        this.valorTotal = 0.0;
    }

    // Construtor com parâmetros
    public Venda(List<ItemVenda> itens) {
        this(); // Chama o construtor padrão
        this.setItens(itens);
    }

    // Setter customizado para garantir a bidirecionalidade e calcular o total
    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
        if (itens != null) {
            itens.forEach(item -> item.setVenda(this)); // Cada item sabe a qual venda pertence
        }
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        this.valorTotal = this.itens.stream()
                .mapToDouble(item -> item.getSubtotal())
                .sum();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataVenda() { return dataVenda; }
    public void setDataVenda(LocalDateTime dataVenda) { this.dataVenda = dataVenda; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public List<ItemVenda> getItens() { return itens; }
    // O setItens personalizado já foi definido acima
}
