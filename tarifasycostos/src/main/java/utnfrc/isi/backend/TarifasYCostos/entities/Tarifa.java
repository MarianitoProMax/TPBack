package utnfrc.isi.backend.TarifasYCostos.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Table(name = "tarifa")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarifa;

    @Column(length = 50)
    private String tipoCamion;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double costoKmBase;

    @Column(precision = 10, scale = 2)
    private Double valorLitroCombustible;

    @Column(precision = 10, scale = 2)
    private Double cargoGestionPorTramo;

    @Column(nullable = false)
    private LocalDate fechaVigenciaDesde;

    private LocalDate fechaVigenciaHasta;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaActualizacion;
}