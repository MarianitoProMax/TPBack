package utnfrc.isi.backend.Usuarios.dto;

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
public class ClienteDTO {
    
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String rol;
    
    @Builder.Default
    private List<Long> idsSolicitudes = new ArrayList<>();
    
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
