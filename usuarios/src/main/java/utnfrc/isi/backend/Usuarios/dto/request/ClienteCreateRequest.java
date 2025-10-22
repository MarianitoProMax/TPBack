package utnfrc.isi.backend.Usuarios.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCreateRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String rol;  // CLIENTE, OPERADOR, ADMINISTRADOR, TRANSPORTISTA
}
