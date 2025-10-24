package utnfrc.isi.backend.FlotaYDepositos.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "deposito") // REVISAR DSP
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Deposito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDeposito;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Lob
    private String direccion;

    @Column(nullable = false, precision = 10)
    private BigDecimal latitud;

    @Column(nullable = false, precision = 10)
    private BigDecimal longitud;

    @Column(nullable = false, precision = 10)
    private BigDecimal costoEstadiaDiario;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaActualizacion;
}
