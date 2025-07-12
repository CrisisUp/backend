// src/main/java/com/mercadoficticio/backend/dto/FornecedorRequestDTO.java
package com.mercadoficticio.backend.dto;

// REMOVA import lombok.Data;
// REMOVA import lombok.NoArgsConstructor;
// REMOVA import lombok.AllArgsConstructor;

public class FornecedorRequestDTO {
    private String nome;
    private String contato;

    // Construtor padrão (sem argumentos)
    public FornecedorRequestDTO() {
    }

    // Construtor com todos os argumentos
    public FornecedorRequestDTO(String nome, String contato) {
        this.nome = nome;
        this.contato = contato;
    }

    // --- GETTERS E SETTERS MANUAIS ---

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
