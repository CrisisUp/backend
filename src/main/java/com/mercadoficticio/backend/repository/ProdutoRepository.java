// src/main/java/com/mercadoficticio/backend/repository/ProdutoRepository.java
package com.mercadoficticio.backend.repository;

import com.mercadoficticio.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Importe List

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // Buscar produtos cujo nome contenha a string (ignorando maiúsculas/minúsculas)
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    // Buscar produtos por ID do fornecedor
    List<Produto> findByFornecedorId(Long fornecedorId);
}
