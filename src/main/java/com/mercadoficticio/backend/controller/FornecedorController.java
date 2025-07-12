// src/main/java/com/mercadoficticio/backend/controller/FornecedorController.java
package com.mercadoficticio.backend.controller;

import com.mercadoficticio.backend.dto.FornecedorRequestDTO;
import com.mercadoficticio.backend.dto.FornecedorResponseDTO;
import com.mercadoficticio.backend.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @Autowired
    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    // Endpoint para criar um novo fornecedor (POST /api/fornecedores)
    @PostMapping
    public ResponseEntity<FornecedorResponseDTO> criarFornecedor(@RequestBody FornecedorRequestDTO requestDTO) {
        try {
            FornecedorResponseDTO novoFornecedor = fornecedorService.criarFornecedor(requestDTO);
            return new ResponseEntity<>(novoFornecedor, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Se o nome do fornecedor já existe (regra de negócio no serviço)
            // Futuramente, você pode retornar e.getMessage() para dar mais detalhes ao frontend.
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 Conflict
        }
        // REMOVIDO: catch (Exception e) { return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
        // Outras exceções não capturadas aqui serão tratadas por um Global Exception Handler (que podemos criar depois)
    }

    // Endpoint para listar todos os fornecedores (GET /api/fornecedores)
    @GetMapping
    public ResponseEntity<List<FornecedorResponseDTO>> listarTodosFornecedores() {
        List<FornecedorResponseDTO> fornecedores = fornecedorService.listarTodosFornecedores();
        return new ResponseEntity<>(fornecedores, HttpStatus.OK);
    }

    // Endpoint para buscar fornecedor por ID (GET /api/fornecedores/{id})
    @GetMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> buscarFornecedorPorId(@PathVariable Long id) {
        try {
            FornecedorResponseDTO fornecedor = fornecedorService.buscarFornecedorPorId(id);
            return new ResponseEntity<>(fornecedor, HttpStatus.OK);
        } catch (RuntimeException e) { // RuntimeException é genérica o suficiente para "não encontrado"
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // Endpoint para atualizar um fornecedor existente (PUT /api/fornecedores/{id})
    @PutMapping("/{id}")
    public ResponseEntity<FornecedorResponseDTO> atualizarFornecedor(@PathVariable Long id, @RequestBody FornecedorRequestDTO requestDTO) {
        try {
            FornecedorResponseDTO fornecedorAtualizado = fornecedorService.atualizarFornecedor(id, requestDTO);
            return new ResponseEntity<>(fornecedorAtualizado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // Se o nome do fornecedor já existe para outro fornecedor
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 Conflict
        } catch (RuntimeException e) {
            // Se o fornecedor não for encontrado (do service)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
        // REMOVIDO: catch (Exception e) { return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
    }

    // Endpoint para deletar um fornecedor (DELETE /api/fornecedores/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFornecedor(@PathVariable Long id) {
        try {
            fornecedorService.deletarFornecedor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
