package utnfrc.isi.backend.FlotaYDepositos.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "camion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCamion;

    @Column(nullable = false, unique = true, length = 20)
    private String dominio;

    @Column(nullable = false, length = 100)
    private String nombreTransportista;

    @Column(length = 20)
    private String telefono;

    @Column(nullable = false, precision = 10)
    private BigDecimal capacidadPeso;

    @Column(nullable = false, precision = 10)
    private BigDecimal capacidadVolumen;

    @Column(nullable = false, precision = 10)
    private BigDecimal costoBaseKm;

    @Column(precision = 10)
    private BigDecimal consumoCombustiblePromedio;

    @Column(nullable = false)
    @Builder.Default
    private Boolean disponible = true;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaActualizacion;

    // ============ RELACIÓN 1:N con Tramo (en microservicio GestionDeEnvios) ============
    // Almacenamos los IDs de los tramos del otro microservicio
    @Column(name = "id_tramo", columnDefinition = "jsonb")
    @Builder.Default

    private List<Long> idsTramos = new ArrayList<>();
}