package utnfrc.isi.backend.GestionDeEnvios.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(name = "solicitud")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;

    @Column(nullable = false, unique = true, length = 20)
    private String numeroSolicitud;


    @OneToOne
    @JoinColumn(name = "id_contenedor", nullable = false, unique = true)
    private Contenedor contenedor;

    @Column(precision = 12, scale = 2)
    private Double costoEstimado;

    private Duration tiempoEstimado;

    @Column(precision = 12, scale = 2)
    private Double costoFinal;

    private Duration tiempoReal;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaActualizacion;

    @OneToOne(mappedBy = "id_solicitud")
    private Ruta ruta;
}