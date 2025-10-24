package utnfrc.isi.backend.FlotaYDepositos.dto;

import java.math.BigDecimal;

public record DepositoDTO(
    Long idDeposito,
    String nombre,
    String direccion,
    BigDecimal latitud,
    BigDecimal longitud,
    BigDecimal costoEstadiaDiario
) {}