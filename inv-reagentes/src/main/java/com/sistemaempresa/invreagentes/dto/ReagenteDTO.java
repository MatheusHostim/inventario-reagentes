package com.sistemaempresa.invreagentes.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReagenteDTO(
        Long id,
        @NotBlank(message = "nome é obrigatório") String nome,
        @Min(value = 0, message = "quantidade deve ser >= 0") int quantidade,
        String lote,
        @NotNull(message = "validade é obrigatória") LocalDate validade,
        @Valid FornecedorDTO fornecedor
) {}