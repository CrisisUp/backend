// src/main/java/com/mercadoficticio/backend/repository/ItemCompraRepository.java
package com.mercadoficticio.backend.repository;

import com.mercadoficticio.backend.model.ItemCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Importe List

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long> {

    // Buscar itens de compra por uma compra específica (ID da compra)
    List<ItemCompra> findByCompraId(Long compraId);

    // Buscar itens de compra por um produto específico (ID do produto)
    List<ItemCompra> findByProdutoId(Long produtoId);
}