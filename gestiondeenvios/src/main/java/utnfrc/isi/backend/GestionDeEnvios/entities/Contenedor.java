package utnfrc.isi.backend.GestionDeEnvios.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "ids_rutas", columnDefinition = "jsonb")
    @Builder.Default
    private List<Long> idsRutas = new ArrayList<>();

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()")
    private LocalDateTime fechaActualizacion;

    @OneToOne(mappedBy = "contenedor")
    private Solicitud solicitud;

    public void agregarRuta(Long idRuta) {
        if (idRuta != null && !this.idsRutas.contains(idRuta)) {
            this.idsRutas.add(idRuta);
        }
    }

    public void removerRuta(Long idRuta) {
        this.idsRutas.remove(idRuta);
    }

    public boolean estaAsignadoARuta(Long idRuta) {
        return this.idsRutas.contains(idRuta);
    }
}