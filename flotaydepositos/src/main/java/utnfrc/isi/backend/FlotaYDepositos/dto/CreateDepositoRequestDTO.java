package utnfrc.isi.backend.FlotaYDepositos.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateDepositoRequestDTO(
    @NotBlank(message = "El nombre no puede estar vacío")
    String nombre,
    @NotBlank(message = "La dirección no puede estar vacía")
    String direccion,
    @NotNull(message = "La latitud no puede ser nula")
    BigDecimal latitud,
    @NotNull(message = "La longitud no puede ser nula")
    BigDecimal longitud,
    @NotNull(message = "El costo de estadía no puede ser nulo")
    @Positive(message = "El costo de estadía debe ser un valor positivo")
    BigDecimal costoEstadiaDiario
) {}