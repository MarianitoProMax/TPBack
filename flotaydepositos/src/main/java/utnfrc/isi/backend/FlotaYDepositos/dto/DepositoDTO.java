package utnfrc.isi.backend.FlotaYDepositos.dto;

public record DepositoDTO(
    Long idDeposito,
    String nombre,
    String direccion,
    Double latitud,
    Double longitud,
    Double costoEstadiaDiario
) {}