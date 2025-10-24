package utnfrc.isi.backend.FlotaYDepositos.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateCamionRequestDTO(
    
    // Esto hace que valide automáticamente los campos al recibir la petición
    @NotBlank(message = "El dominio no puede estar vacío")
    @Size(max = 20, message = "El dominio no puede tener más de 20 caracteres")
    String dominio,

    @NotBlank(message = "El nombre del transportista no puede estar vacío")
    String nombreTransportista,

    String telefono,

    @NotNull(message = "La capacidad de peso no puede ser nula")
    @Positive(message = "La capacidad de peso debe ser positiva")
    BigDecimal capacidadPeso,

    @NotNull(message = "La capacidad de volumen no puede ser nula")
    @Positive(message = "La capacidad de volumen debe ser positiva")
    BigDecimal capacidadVolumen,

    @NotNull(message = "La disponibilidad no puede ser nula")
    Boolean disponible,

    @NotNull(message = "El costo por km no puede ser nulo")
    @Positive(message = "El costo por km debe ser positivo")
    BigDecimal costoPorKm,

    @NotNull(message = "El consumo de combustible no puede ser nulo")
    @Positive(message = "El consumo de combustible debe ser positivo")
    BigDecimal consumoCombustiblePorKm
) {}