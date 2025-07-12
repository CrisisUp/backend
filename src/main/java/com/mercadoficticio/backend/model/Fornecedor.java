// src/main/java/com/mercadoficticio/backend/model/Fornecedor.java
package com.mercadoficticio.backend.model;

import jakarta.persistence.*; // IMPORTE ISSO!

@Entity // <--- ESTA ANOTAÇÃO É OBRIGATÓRIA E DEVE ESTAR AQUI!
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    private String contato;

    // Construtor padrão (SEM ARGUMENTOS) - OBRIGATÓRIO PARA JPA
    public Fornecedor() {
    }

    // Construtor com todos os campos (útil para criar objetos)
    public Fornecedor(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    // --- GETTERS E SETTERS MANUAIS ---
    // Você precisa ter getters e setters para TODOS os campos!
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

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}