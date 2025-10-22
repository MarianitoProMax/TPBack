package utnfrc.isi.backend.GestionDeEnvios.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RutaDTO {
    
    private Long idRuta;
    private Long idSolicitud;
    private Integer cantidadTramos;
    private Integer cantidadDepositos;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
