// src/main/java/com/mercadoficticio/backend/service/VendaService.java
package com.mercadoficticio.backend.service;

import com.mercadoficticio.backend.model.Venda;
import com.mercadoficticio.backend.model.ItemVenda;
import com.mercadoficticio.backend.model.Produto;
import com.mercadoficticio.backend.model.MovimentacaoFinanceira;
import com.mercadoficticio.backend.model.TipoMovimentacao;
import com.mercadoficticio.backend.model.CategoriaMovimentacao;
import com.mercadoficticio.backend.repository.VendaRepository;
import com.mercadoficticio.backend.repository.ProdutoRepository;
import com.mercadoficticio.backend.repository.MovimentacaoFinanceiraRepository;
import com.mercadoficticio.backend.dto.VendaRequestDTO;
import com.mercadoficticio.backend.dto.VendaResponseDTO;
import com.mercadoficticio.backend.dto.ItemVendaRequestDTO;
import com.mercadoficticio.backend.dto.ItemVendaResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final MovimentacaoFinanceiraRepository movimentacaoFinanceiraRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository, ProdutoRepository produtoRepository,
                        MovimentacaoFinanceiraRepository movimentacaoFinanceiraRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.movimentacaoFinanceiraRepository = movimentacaoFinanceiraRepository;
    }

    @Transactional // Garante que a operação (venda, estoque, fluxo de caixa) seja atômica
    public VendaResponseDTO registrarVenda(VendaRequestDTO requestDTO) {
        Venda venda = new Venda();
        // dataVenda é setada no construtor padrão da entidade Venda

        List<ItemVenda> itensDaVenda = new ArrayList<>();
        double valorTotalVenda = 0.0;

        // Processar cada Item da Venda
        for (ItemVendaRequestDTO itemDTO : requestDTO.getItens()) {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + itemDTO.getProdutoId()));

            // Regra de Negócio: Verificar Estoque
            if (produto.getQuantidadeEstoque() < itemDTO.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome() + ". Disponível: " + produto.getQuantidadeEstoque() + ", Solicitado: " + itemDTO.getQuantidade());
            }

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(itemDTO.getQuantidade());
            // Se o precoUnitarioVenda não for fornecido no DTO, pode-se usar o precoVenda do produto
            itemVenda.setPrecoUnitarioVenda(itemDTO.getPrecoUnitarioVenda() != null ? itemDTO.getPrecoUnitarioVenda() : produto.getPrecoVenda());
            itemVenda.setVenda(venda); // Associa o item à venda

            itensDaVenda.add(itemVenda);

            // Atualizar Estoque do Produto
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - itemDTO.getQuantidade());
            produtoRepository.save(produto); // Salva a atualização do estoque

            valorTotalVenda += itemVenda.getSubtotal(); // Soma ao valor total da venda
        }

        venda.setItens(itensDaVenda); // Adiciona os itens à venda
        venda.calcularValorTotal(); // Garante que o valor total da venda seja calculado

        Venda vendaSalva = vendaRepository.save(venda); // Salva a venda principal e seus itens em cascata

        // Registrar Movimentação Financeira (Receita)
        MovimentacaoFinanceira receita = new MovimentacaoFinanceira(
                "Venda de produtos (Total: " + vendaSalva.getItens().size() + " itens)",
                vendaSalva.getValorTotal(),
                TipoMovimentacao.RECEITA,
                CategoriaMovimentacao.VENDAS
        );
        movimentacaoFinanceiraRepository.save(receita); // Salva a receita no fluxo de caixa

        return mapearVendaParaResponseDTO(vendaSalva);
    }

    @Transactional(readOnly = true)
    public List<VendaResponseDTO> listarTodasVendas() {
        List<Venda> vendas = vendaRepository.findAll();
        return vendas.stream()
                .map(this::mapearVendaParaResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VendaResponseDTO buscarVendaPorId(Long id) {
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada com ID: " + id));
        return mapearVendaParaResponseDTO(venda);
    }

    // Método auxiliar para mapear Venda para VendaResponseDTO
    private VendaResponseDTO mapearVendaParaResponseDTO(Venda venda) {
        List<ItemVendaResponseDTO> itensResponse = venda.getItens().stream()
                .map(item -> new ItemVendaResponseDTO(
                        item.getId(),
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getPrecoUnitarioVenda(),
                        item.getSubtotal()
                ))
                .collect(Collectors.toList());

        return new VendaResponseDTO(
                venda.getId(),
                venda.getDataVenda(),
                venda.getValorTotal(),
                itensResponse
        );
    }
}
