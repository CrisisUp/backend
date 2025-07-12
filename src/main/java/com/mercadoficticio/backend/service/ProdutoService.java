// src/main/java/com/mercadoficticio/backend/service/ProdutoService.java
package com.mercadoficticio.backend.service;

import com.mercadoficticio.backend.model.Fornecedor;
import com.mercadoficticio.backend.model.Produto;
import com.mercadoficticio.backend.repository.FornecedorRepository;
import com.mercadoficticio.backend.repository.ProdutoRepository;
import com.mercadoficticio.backend.dto.ProdutoRequestDTO;
import com.mercadoficticio.backend.dto.ProdutoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository; // Para buscar o fornecedor

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, FornecedorRepository fornecedorRepository) {
        this.produtoRepository = produtoRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    // --- Métodos de Lógica de Negócio para Produto ---

    @Transactional
    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO requestDTO) {
        // Regra de negócio: Verificar se o fornecedor existe
        Fornecedor fornecedor = fornecedorRepository.findById(requestDTO.getFornecedorId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com o ID: " + requestDTO.getFornecedorId()));

        Produto produto = new Produto();
        produto.setNome(requestDTO.getNome());
        produto.setDescricao(requestDTO.getDescricao());
        produto.setPrecoCusto(requestDTO.getPrecoCusto());
        produto.setPrecoVenda(requestDTO.getPrecoVenda());
        produto.setQuantidadeEstoque(requestDTO.getQuantidadeEstoque());
        produto.setFornecedor(fornecedor); // Associa o fornecedor encontrado

        Produto produtoSalvo = produtoRepository.save(produto);
        return mapearProdutoParaResponseDTO(produtoSalvo);
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> listarTodosProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(this::mapearProdutoParaResponseDTO) // Usa o método auxiliar de mapeamento
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProdutoResponseDTO buscarProdutoPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
        return mapearProdutoParaResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO requestDTO) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));

        Fornecedor fornecedor = fornecedorRepository.findById(requestDTO.getFornecedorId())
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado com o ID: " + requestDTO.getFornecedorId()));

        produtoExistente.setNome(requestDTO.getNome());
        produtoExistente.setDescricao(requestDTO.getDescricao());
        produtoExistente.setPrecoCusto(requestDTO.getPrecoCusto());
        produtoExistente.setPrecoVenda(requestDTO.getPrecoVenda());
        produtoExistente.setQuantidadeEstoque(requestDTO.getQuantidadeEstoque());
        produtoExistente.setFornecedor(fornecedor);

        Produto produtoAtualizado = produtoRepository.save(produtoExistente);
        return mapearProdutoParaResponseDTO(produtoAtualizado);
    }

    @Transactional
    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
        produtoRepository.deleteById(id);
    }

    // --- Método Auxiliar de Mapeamento (para evitar repetição de código) ---
    private ProdutoResponseDTO mapearProdutoParaResponseDTO(Produto produto) {
        // Garante que o fornecedor não é nulo antes de tentar pegar o nome
        String nomeFornecedor = (produto.getFornecedor() != null) ? produto.getFornecedor().getNome() : null;

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPrecoCusto(),
                produto.getPrecoVenda(),
                produto.getQuantidadeEstoque(),
                produto.calcularMargemLucro(), // Usa o método da entidade
                produto.getFornecedor() != null ? produto.getFornecedor().getId() : null,
                nomeFornecedor
        );
    }
}
