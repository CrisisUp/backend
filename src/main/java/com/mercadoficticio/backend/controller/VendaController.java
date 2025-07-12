// src/main/java/com/mercadoficticio/backend/controller/VendaController.java
package com.mercadoficticio.backend.controller;

import com.mercadoficticio.backend.dto.VendaRequestDTO;
import com.mercadoficticio.backend.dto.VendaResponseDTO;
import com.mercadoficticio.backend.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/vendas") // Define o caminho base para todos os endpoints de venda
public class VendaController {

    private final VendaService vendaService;

    @Autowired // Injeta o VendaService
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    // Endpoint para registrar uma nova venda (POST /api/vendas)
    @PostMapping
    public ResponseEntity<VendaResponseDTO> registrarVenda(@RequestBody VendaRequestDTO requestDTO) {
        try {
            VendaResponseDTO novaVenda = vendaService.registrarVenda(requestDTO);
            return new ResponseEntity<>(novaVenda, HttpStatus.CREATED); // Retorna 201 Created
        } catch (RuntimeException e) {
            // Captura RuntimeException se o produto ou estoque for insuficiente (do service)
            // Você pode adicionar mais detalhes ao frontend com e.getMessage() se quiser.
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Retorna 400 Bad Request
        } catch (Exception e) {
            // Outros erros inesperados do servidor
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Retorna 500 Internal Server Error
        }
    }

    // Endpoint para listar todas as vendas (GET /api/vendas)
    @GetMapping
    public ResponseEntity<List<VendaResponseDTO>> listarTodasVendas() {
        List<VendaResponseDTO> vendas = vendaService.listarTodasVendas();
        return new ResponseEntity<>(vendas, HttpStatus.OK); // Retorna 200 OK
    }

    // Endpoint para buscar venda por ID (GET /api/vendas/{id})
    @GetMapping("/{id}")
    public ResponseEntity<VendaResponseDTO> buscarVendaPorId(@PathVariable Long id) {
        try {
            VendaResponseDTO venda = vendaService.buscarVendaPorId(id);
            return new ResponseEntity<>(venda, HttpStatus.OK); // Retorna 200 OK
        } catch (RuntimeException e) {
            // Se a venda não for encontrada
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 Not Found
        }
    }

    // Futuramente, pode adicionar PUT para atualizar ou DELETE para cancelar venda,
    // mas a lógica de estoque e financeiro precisaria ser revertida.
}
