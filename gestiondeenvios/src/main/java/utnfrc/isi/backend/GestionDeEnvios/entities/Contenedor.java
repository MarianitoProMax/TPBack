package utnfrc.isi.backend.GestionDeEnvios.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contenedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContenedor;

    @Column(nullable = false, unique = true, length = 50)
    private String identificacionUnica;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double peso;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double volumen;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaActualizacion;

    @OneToOne(mappedBy = "contenedor")
    private Solicitud solicitud;
}