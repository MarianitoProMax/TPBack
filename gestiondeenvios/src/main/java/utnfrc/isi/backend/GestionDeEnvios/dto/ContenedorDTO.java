package utnfrc.isi.backend.GestionDeEnvios.dto;

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
public class ContenedorDTO {
    
    private Long idContenedor;
    private String identificacionUnica;
    private Double peso;
    private Double volumen;
    private String estado;
    
    @Builder.Default
    private List<Long> idsRutas = new ArrayList<>();
    
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
