// src/main/java/com/mercadoficticio/backend/controller/CompraController.java
package com.mercadoficticio.backend.controller;

import com.mercadoficticio.backend.dto.CompraRequestDTO;
import com.mercadoficticio.backend.dto.CompraResponseDTO;
import com.mercadoficticio.backend.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras") // Define o caminho base para todos os endpoints de compra
public class CompraController {

    private final CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    // Endpoint para registrar uma nova compra (POST /api/compras)
    @PostMapping
    public ResponseEntity<CompraResponseDTO> registrarCompra(@RequestBody CompraRequestDTO requestDTO) {
        try {
            CompraResponseDTO novaCompra = compraService.registrarCompra(requestDTO);
            return new ResponseEntity<>(novaCompra, HttpStatus.CREATED); // Retorna 201 Created
        } catch (RuntimeException e) {
            // Captura exceções de negócio (fornecedor/produto não encontrado)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Retorna 400 Bad Request
        } catch (Exception e) {
            // Outros erros internos do servidor
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500 Internal Server Error
        }
    }

    // Endpoint para listar todas as compras (GET /api/compras)
    @GetMapping
    public ResponseEntity<List<CompraResponseDTO>> listarTodasCompras() {
        List<CompraResponseDTO> compras = compraService.listarTodasCompras();
        return new ResponseEntity<>(compras, HttpStatus.OK); // Retorna 200 OK
    }

    // Endpoint para buscar compra por ID (GET /api/compras/{id})
    @GetMapping("/{id}")
    public ResponseEntity<CompraResponseDTO> buscarCompraPorId(@PathVariable Long id) {
        try {
            CompraResponseDTO compra = compraService.buscarCompraPorId(id);
            return new ResponseEntity<>(compra, HttpStatus.OK); // Retorna 200 OK
        } catch (RuntimeException e) {
            // Se a compra não for encontrada
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 Not Found
        }
    }
}