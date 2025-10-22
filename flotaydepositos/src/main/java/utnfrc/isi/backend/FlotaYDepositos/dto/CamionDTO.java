package utnfrc.isi.backend.FlotaYDepositos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CamionDTO {
    private Long idCamion;
    private String dominio;
    private String nombreTransportista;
    private String telefono;
    private Double capacidadPeso;
    private Double capacidadVolumen;
    private Double costoBaseKm;
    private Double consumoCombustiblePromedio;
    private Boolean disponible;
    
    @Builder.Default
    private List<Long> idsTramos = new ArrayList<>();
    
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
