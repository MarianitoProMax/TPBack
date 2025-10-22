package utnfrc.isi.backend.FlotaYDepositos.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
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

    @Column(nullable = false, precision = 10, scale = 2)
    private Double capacidadPeso;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double capacidadVolumen;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double costoBaseKm;

    @Column(precision = 10, scale = 2)
    private Double consumoCombustiblePromedio;

    @Column(nullable = false)
    private Boolean disponible = true;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaActualizacion;

}