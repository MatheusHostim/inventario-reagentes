package com.sistemaempresa.invreagentes.service;

import com.sistemaempresa.invreagentes.dto.ReagenteDTO;
import com.sistemaempresa.invreagentes.mapper.ReagenteMapper;
import com.sistemaempresa.invreagentes.mapper.FornecedorMapper;
import com.sistemaempresa.invreagentes.model.Fornecedor;
import com.sistemaempresa.invreagentes.model.Reagente;
import com.sistemaempresa.invreagentes.repository.FornecedorRepository;
import com.sistemaempresa.invreagentes.repository.ReagenteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReagenteService {

    private final ReagenteRepository repository;
    private final FornecedorRepository fornecedorRepository;

    public ReagenteService(ReagenteRepository repository, FornecedorRepository fornecedorRepository) {
        this.repository = repository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public List<ReagenteDTO> listarTodos() {
        return repository.findAll().stream().map(ReagenteMapper::toDTO).collect(Collectors.toList());
    }

    public ReagenteDTO buscarPorId(Long id) {
        Reagente r = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reagente não encontrado"));
        return ReagenteMapper.toDTO(r);
    }

    @Transactional
    public ReagenteDTO criar(ReagenteDTO dto) {
        Reagente r = ReagenteMapper.toEntity(dto);

        if (dto.fornecedor() != null) {
            Long fornecId = dto.fornecedor().id();
            if (fornecId != null) {
                Fornecedor f = fornecedorRepository.findById(fornecId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado"));
                r.setFornecedor(f);
            } else {
                Fornecedor novo = FornecedorMapper.toEntity(dto.fornecedor());
                Fornecedor salvo = fornecedorRepository.save(novo);
                r.setFornecedor(salvo);
            }
        }

        Reagente salvo = repository.save(r);
        return ReagenteMapper.toDTO(salvo);
    }

    @Transactional
    public ReagenteDTO atualizar(Long id, ReagenteDTO dto) {
        Reagente existente = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reagente não encontrado"));

        existente.setNome(dto.nome());
        existente.setQuantidade(dto.quantidade());
        existente.setLote(dto.lote());
        existente.setValidade(dto.validade());

        if (dto.fornecedor() != null) {
            Long fornecId = dto.fornecedor().id();
            if (fornecId != null) {
                Fornecedor f = fornecedorRepository.findById(fornecId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Fornecedor não encontrado"));
                existente.setFornecedor(f);
            } else {
                Fornecedor novo = FornecedorMapper.toEntity(dto.fornecedor());
                Fornecedor salvo = fornecedorRepository.save(novo);
                existente.setFornecedor(salvo);
            }
        } else {
            existente.setFornecedor(null);
        }

        Reagente salvo = repository.save(existente);
        return ReagenteMapper.toDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reagente não encontrado");
        }
        repository.deleteById(id);
    }
}