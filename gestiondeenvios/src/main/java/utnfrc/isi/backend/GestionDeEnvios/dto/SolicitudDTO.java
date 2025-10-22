package utnfrc.isi.backend.GestionDeEnvios.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Duration;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudDTO {
    
    private Long idSolicitud;
    private String numeroSolicitud;
    private Long idCliente;
    private Long idContenedor;
    private Double costoEstimado;
    private Duration tiempoEstimado;
    private Double costoFinal;
    private Duration tiempoReal;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
