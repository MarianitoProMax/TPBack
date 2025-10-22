package utnfrc.isi.backend.GestionDeEnvios.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ruta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRuta;

    @OneToOne
    @JoinColumn(name = "id_solicitud", nullable = false, unique = true)
    private Solicitud solicitud;

    @Column(nullable = false)
    private Integer cantidadTramos;

    @Column(nullable = false)
    private Integer cantidadDepositos;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaActualizacion;

    @OneToMany(mappedBy = "ruta")
    private List<Tramo> tramos;
}