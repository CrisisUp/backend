// src/main/java/com/mercadoficticio/backend/controller/ProdutoController.java
package com.mercadoficticio.backend.controller;

import com.mercadoficticio.backend.dto.ProdutoRequestDTO;
import com.mercadoficticio.backend.dto.ProdutoResponseDTO;
import com.mercadoficticio.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos") // Define o caminho base para todos os endpoints de produto
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // Endpoint para criar um novo produto (POST /api/produtos)
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody ProdutoRequestDTO requestDTO) {
        try {
            ProdutoResponseDTO novoProduto = produtoService.criarProduto(requestDTO);
            return new ResponseEntity<>(novoProduto, HttpStatus.CREATED); // Retorna 201 Created
        } catch (RuntimeException e) {
            // Captura RuntimeException se o fornecedor não for encontrado
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Ou um erro mais específico como 400 Bad Request
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para listar todos os produtos (GET /api/produtos)
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodosProdutos() {
        List<ProdutoResponseDTO> produtos = produtoService.listarTodosProdutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    // Endpoint para buscar produto por ID (GET /api/produtos/{id})
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarProdutoPorId(@PathVariable Long id) {
        try {
            ProdutoResponseDTO produto = produtoService.buscarProdutoPorId(id);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 Not Found
        }
    }

    // Endpoint para atualizar um produto existente (PUT /api/produtos/{id})
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO requestDTO) {
        try {
            ProdutoResponseDTO produtoAtualizado = produtoService.atualizarProduto(id, requestDTO);
            return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Pode ser 404 Not Found (produto/fornecedor) ou 400 Bad Request
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para deletar um produto (DELETE /api/produtos/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        try {
            produtoService.deletarProduto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 No Content
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}