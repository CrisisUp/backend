// src/main/java/com/mercadoficticio/backend/service/CompraService.java
package com.mercadoficticio.backend.service;

import com.mercadoficticio.backend.model.Compra;
import com.mercadoficticio.backend.model.Fornecedor;
import com.mercadoficticio.backend.model.ItemCompra;
import com.mercadoficticio.backend.model.MovimentacaoFinanceira;
import com.mercadoficticio.backend.model.Produto;
import com.mercadoficticio.backend.model.TipoMovimentacao;
import com.mercadoficticio.backend.model.CategoriaMovimentacao;
import com.mercadoficticio.backend.repository.CompraRepository;
import com.mercadoficticio.backend.repository.FornecedorRepository;
import com.mercadoficticio.backend.repository.ProdutoRepository;
import com.mercadoficticio.backend.repository.MovimentacaoFinanceiraRepository;
import com.mercadoficticio.backend.dto.CompraRequestDTO;
import com.mercadoficticio.backend.dto.CompraResponseDTO;
import com.mercadoficticio.backend.dto.ItemCompraRequestDTO;
import com.mercadoficticio.backend.dto.ItemCompraResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompraService {

    private final CompraRepository compraRepository;
    private final FornecedorRepository fornecedorRepository;
    private final ProdutoRepository produtoRepository;
    private final MovimentacaoFinanceiraRepository movimentacaoFinanceiraRepository;

    @Autowired
    public CompraService(CompraRepository compraRepository, FornecedorRepository fornecedorRepository,
                         ProdutoRepository produtoRepository, MovimentacaoFinanceiraRepository movimentacaoFinanceiraRepository) {
        this.compraRepository = compraRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.produtoRepository = produtoRepository;
        this.movimentacaoFinanceiraRepository = movimentacaoFinanceiraRepository;
    }

    @Transactional // Garante que toda a operação (compra, estoque, fluxo de caixa) seja atômica
    public CompraResponseDTO registrarCompra(CompraRequestDTO requestDTO) {
        // 1. Validar e Buscar Fornecedor
        Fornecedor fornecedor = fornecedorRepository.findById(requestDTO.getFornecedorId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com ID: " + requestDTO.getFornecedorId()));

        // 2. Criar a Compra (Entidade principal)
        Compra compra = new Compra();
        compra.setFornecedor(fornecedor); // Associa o fornecedor
        // dataCompra é setada no construtor padrão da entidade Compra

        List<ItemCompra> itensDaCompra = new ArrayList<>();
        double valorTotalCompra = 0.0;

        // 3. Processar cada Item da Compra
        for (ItemCompraRequestDTO itemDTO : requestDTO.getItens()) {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + itemDTO.getProdutoId()));

            ItemCompra itemCompra = new ItemCompra();
            itemCompra.setProduto(produto);
            itemCompra.setQuantidade(itemDTO.getQuantidade());
            itemCompra.setPrecoUnitarioCusto(itemDTO.getPrecoUnitarioCusto());
            itemCompra.setCompra(compra); // Associa o item à compra

            itensDaCompra.add(itemCompra);

            // 4. Atualizar Estoque do Produto
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + itemDTO.getQuantidade());
            produtoRepository.save(produto); // Salva a atualização do estoque

            valorTotalCompra += itemCompra.getSubtotal(); // Soma ao valor total da compra
        }

        compra.setItens(itensDaCompra); // Adiciona os itens à compra
        compra.calcularValorTotal(); // Garante que o valor total da compra seja calculado

        Compra compraSalva = compraRepository.save(compra); // Salva a compra principal e seus itens em cascata

        // 5. Registrar Movimentação Financeira (Despesa)
        MovimentacaoFinanceira despesa = new MovimentacaoFinanceira(
                "Compra de produtos do fornecedor " + fornecedor.getNome() + " (ID: " + fornecedor.getId() + ")",
                compraSalva.getValorTotal(),
                TipoMovimentacao.DESPESA,
                CategoriaMovimentacao.COMPRAS
        );
        movimentacaoFinanceiraRepository.save(despesa); // Salva a despesa no fluxo de caixa

        // 6. Mapear para ResponseDTO
        return mapearCompraParaResponseDTO(compraSalva);
    }

    @Transactional(readOnly = true)
    public List<CompraResponseDTO> listarTodasCompras() {
        List<Compra> compras = compraRepository.findAll();
        return compras.stream()
                .map(this::mapearCompraParaResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CompraResponseDTO buscarCompraPorId(Long id) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada com ID: " + id));
        return mapearCompraParaResponseDTO(compra);
    }

    // Método auxiliar para mapear Compra para CompraResponseDTO
    private CompraResponseDTO mapearCompraParaResponseDTO(Compra compra) {
        String nomeFornecedor = (compra.getFornecedor() != null) ? compra.getFornecedor().getNome() : null;

        List<ItemCompraResponseDTO> itensResponse = compra.getItens().stream()
                .map(item -> new ItemCompraResponseDTO(
                        item.getId(),
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getPrecoUnitarioCusto(),
                        item.getSubtotal()
                ))
                .collect(Collectors.toList());

        return new CompraResponseDTO(
                compra.getId(),
                compra.getDataCompra(),
                compra.getValorTotal(),
                compra.getFornecedor() != null ? compra.getFornecedor().getId() : null,
                nomeFornecedor,
                itensResponse
        );
    }
}