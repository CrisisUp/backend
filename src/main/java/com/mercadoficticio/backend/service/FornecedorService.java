// src/main/java/com/mercadoficticio/backend/service/FornecedorService.java
package com.mercadoficticio.backend.service;

import com.mercadoficticio.backend.model.Fornecedor;
import com.mercadoficticio.backend.repository.FornecedorRepository;
import com.mercadoficticio.backend.dto.FornecedorRequestDTO;
import com.mercadoficticio.backend.dto.FornecedorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired; // Para injeção de dependências
import org.springframework.stereotype.Service; // Indica que esta classe é um serviço Spring
import org.springframework.transaction.annotation.Transactional; // Para controle de transações

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors; // Para mapear listas

@Service // Marca a classe como um componente de serviço Spring
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Autowired // O Spring injetará uma instância de FornecedorRepository aqui
    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    // --- Métodos de Lógica de Negócio para Fornecedor ---

    @Transactional // Garante que a operação seja atômica no banco de dados
    public FornecedorResponseDTO criarFornecedor(FornecedorRequestDTO requestDTO) {
        // Regra de negócio: Verificar se já existe um fornecedor com o mesmo nome
        if (fornecedorRepository.findByNome(requestDTO.getNome()).isPresent()) {
            throw new IllegalArgumentException("Fornecedor com o nome '" + requestDTO.getNome() + "' já existe.");
        }

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(requestDTO.getNome());
        fornecedor.setContato(requestDTO.getContato());

        Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedor);
        return new FornecedorResponseDTO(fornecedorSalvo.getId(), fornecedorSalvo.getNome(), fornecedorSalvo.getContato());
    }

    @Transactional(readOnly = true) // Apenas leitura, otimiza performance
    public List<FornecedorResponseDTO> listarTodosFornecedores() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        // Mapeia a lista de entidades Fornecedor para uma lista de FornecedorResponseDTO
        return fornecedores.stream()
                .map(fornecedor -> new FornecedorResponseDTO(fornecedor.getId(), fornecedor.getNome(), fornecedor.getContato()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FornecedorResponseDTO buscarFornecedorPorId(Long id) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);
        if (fornecedorOptional.isEmpty()) {
            throw new RuntimeException("Fornecedor não encontrado com o ID: " + id);
        }
        Fornecedor fornecedor = fornecedorOptional.get();
        return new FornecedorResponseDTO(fornecedor.getId(), fornecedor.getNome(), fornecedor.getContato());
    }

    @Transactional
    public FornecedorResponseDTO atualizarFornecedor(Long id, FornecedorRequestDTO requestDTO) {
        Optional<Fornecedor> fornecedorOptional = fornecedorRepository.findById(id);
        if (fornecedorOptional.isEmpty()) {
            throw new RuntimeException("Fornecedor não encontrado com o ID: " + id);
        }
        Fornecedor fornecedor = fornecedorOptional.get();

        // Regra de negócio: Verificar se o novo nome já existe e não é o mesmo fornecedor
        if (fornecedorRepository.findByNome(requestDTO.getNome()).isPresent() &&
                !fornecedorRepository.findByNome(requestDTO.getNome()).get().getId().equals(id)) {
            throw new IllegalArgumentException("Já existe outro fornecedor com o nome '" + requestDTO.getNome() + "'.");
        }

        fornecedor.setNome(requestDTO.getNome());
        fornecedor.setContato(requestDTO.getContato());

        Fornecedor fornecedorAtualizado = fornecedorRepository.save(fornecedor);
        return new FornecedorResponseDTO(fornecedorAtualizado.getId(), fornecedorAtualizado.getNome(), fornecedorAtualizado.getContato());
    }

    @Transactional
    public void deletarFornecedor(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new RuntimeException("Fornecedor não encontrado com o ID: " + id);
        }
        // Futuramente: Adicionar lógica para verificar se há produtos associados antes de deletar
        fornecedorRepository.deleteById(id);
    }
}
