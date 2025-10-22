package utnfrc.isi.backend.Usuarios.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, unique = true, length = 20)
    private String dni;

    @Column(length = 20)
    private String telefono;

    @Column(length = 100)
    private String email;

    @Lob
    private String direccion;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaActualizacion;

}