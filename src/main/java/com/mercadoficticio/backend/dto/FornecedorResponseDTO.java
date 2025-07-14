// src/main/java/com/mercadoficticio/backend/dto/FornecedorResponseDTO.java
package com.mercadoficticio.backend.dto;

public class FornecedorResponseDTO {
    private Long id;
    private String nome;
    private String contato;

    // Construtor padrão
    public FornecedorResponseDTO() {
    }

    // Construtor com todos os argumentos
    public FornecedorResponseDTO(Long id, String nome, String contato) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
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

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}