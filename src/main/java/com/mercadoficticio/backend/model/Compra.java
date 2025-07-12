// src/main/java/com/mercadoficticio/backend/model/Compra.java
package com.mercadoficticio.backend.model;

import jakarta.persistence.*; // IMPORTE ISSO!
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity // <--- ESTA ANOTAÇÃO É OBRIGATÓRIA E DEVE ESTAR AQUI!
// Remova @Data se ainda estiver.
// Remova @NoArgsConstructor e @AllArgsConstructor se ainda estiver.
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    @Column(nullable = false)
    private Double valorTotal;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemCompra> itens = new ArrayList<>();

    // Construtor padrão (SEM ARGUMENTOS) - OBRIGATÓRIO PARA JPA
    public Compra() {
        this.dataCompra = LocalDateTime.now(); // Pode inicializar aqui, se quiser
        this.valorTotal = 0.0; // Valor inicial
    }

    // Construtor com parâmetros (se necessário, mas o padrão é o mais crítico para JPA)
    public Compra(Fornecedor fornecedor, List<ItemCompra> itens) {
        this(); // Chama o construtor padrão para inicializar dataCompra e valorTotal
        this.fornecedor = fornecedor;
        this.setItens(itens);
    }

    // --- GETTERS E SETTERS MANUAIS ---
    // Você precisa ter getters e setters para TODOS os campos!
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getDataCompra() { return dataCompra; }
    public void setDataCompra(LocalDateTime dataCompra) { this.dataCompra = dataCompra; }

    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public List<ItemCompra> getItens() { return itens; }
    public void setItens(List<ItemCompra> itens) {
        this.itens = itens;
        if (itens != null) {
            // Garante a bidirecionalidade do relacionamento
            itens.forEach(item -> item.setCompra(this));
        }
        calcularValorTotal(); // Recalcula o total ao definir os itens
    }

    public void calcularValorTotal() {
        this.valorTotal = this.itens.stream()
                .mapToDouble(item -> item.getPrecoUnitarioCusto() * item.getQuantidade())
                .sum();
    }
}