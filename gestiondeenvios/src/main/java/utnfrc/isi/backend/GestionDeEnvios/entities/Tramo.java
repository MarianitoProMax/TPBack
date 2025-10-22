package utnfrc.isi.backend.GestionDeEnvios.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime; 

@Entity
@Table(name = "tramo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tramo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTramo;

    @ManyToOne
    @JoinColumn(name = "id_ruta", nullable = false)
    private Ruta ruta;

    @Column(nullable = false, precision = 10, scale = 7)
    private Double origenLat;

    @Column(nullable = false, precision = 10, scale = 7)
    private Double origenLong;

    @Column(nullable = false, precision = 10, scale = 7)
    private Double destinoLat;

    @Column(nullable = false, precision = 10, scale = 7)
    private Double destinoLong;

    @Column(nullable = false, length = 30)
    private String tipo;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(precision = 12, scale = 2)
    private Double costoAproximado;

    @Column(precision = 12, scale = 2)
    private Double costoReal;

    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaActualizacion;
}