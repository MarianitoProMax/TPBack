package utnfrc.isi.backend.FlotaYDepositos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateCamionRequestDTO(
    @NotBlank(message = "El dominio no puede estar vacío")
    @Size(max = 20, message = "El dominio no puede tener más de 20 caracteres")
    String dominio,

    @NotBlank(message = "El nombre del transportista no puede estar vacío")
    String nombreTransportista,

    String telefono,

    @NotNull(message = "La capacidad de peso no puede ser nula")
    @Positive(message = "La capacidad de peso debe ser positiva")
    Double capacidadPeso,

    @NotNull(message = "La capacidad de volumen no puede ser nula")
    @Positive(message = "La capacidad de volumen debe ser positiva")
    Double capacidadVolumen,

    @NotNull(message = "La disponibilidad no puede ser nula")
    Boolean disponibilidad,

    @NotNull(message = "El costo por km no puede ser nulo")
    @Positive(message = "El costo por km debe ser positivo")
    Double costoPorKm,

    @NotNull(message = "El consumo de combustible no puede ser nulo")
    @Positive(message = "El consumo de combustible debe ser positivo")
    Double consumoCombustiblePorKm
) {}