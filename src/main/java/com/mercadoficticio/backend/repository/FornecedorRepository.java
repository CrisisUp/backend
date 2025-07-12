// src/main/java/com/mercadoficticio/backend/repository/FornecedorRepository.java
package com.mercadoficticio.backend.repository;

import com.mercadoficticio.backend.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; // Importe Optional para findByNome

@Repository // Indica que esta interface é um componente de repositório do Spring
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    // JpaRepository<Tipo da Entidade, Tipo do ID da Entidade>

    // Método personalizado: Encontrar um fornecedor pelo nome
    // Optional é usado para indicar que o fornecedor pode não ser encontrado
    Optional<Fornecedor> findByNome(String nome);
}