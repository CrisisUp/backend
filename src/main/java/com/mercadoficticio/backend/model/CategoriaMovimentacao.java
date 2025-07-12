// src/main/java/com/mercadoficticio/backend/model/CategoriaMovimentacao.java
package com.mercadoficticio.backend.model;

public enum CategoriaMovimentacao {
    VENDAS,
    COMPRAS,
    DESPESA_FIXA, // Aluguel, salários, contas de luz/água, internet, etc.
    DESPESA_VARIAVEL, // Manutenção, marketing, etc.
    OUTRAS_RECEITAS, // Empréstimos, rendimentos, etc.
    IMPOSTOS,
    SALARIOS
    // Adicione mais categorias conforme a necessidade do mercado
}
