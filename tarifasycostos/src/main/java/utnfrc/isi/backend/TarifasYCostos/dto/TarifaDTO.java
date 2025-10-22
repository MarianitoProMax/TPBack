package utnfrc.isi.backend.TarifasYCostos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarifaDTO {
    private Long idTarifa;
    private String tipoCamion;
    private Double costoKmBase;
    private Double valorLitroCombustible;
    private Double cargoGestionPorTramo;
    private LocalDate fechaVigenciaDesde;
    private LocalDate fechaVigenciaHasta;
    
    @Builder.Default
    private List<Long> idsCamiones = new ArrayList<>();
    
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
