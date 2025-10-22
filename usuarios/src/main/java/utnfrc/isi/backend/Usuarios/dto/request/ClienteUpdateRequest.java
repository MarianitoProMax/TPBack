package utnfrc.isi.backend.Usuarios.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteUpdateRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}
