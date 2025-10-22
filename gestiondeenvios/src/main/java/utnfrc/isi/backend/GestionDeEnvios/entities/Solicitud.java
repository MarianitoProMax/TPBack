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

    @Column(name = "id_cliente")
    private Long idCliente;

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

    @OneToOne(mappedBy = "id_ruta")
    private Ruta ruta;

    public void asignarCliente(Long idClienteAsignado) {
        this.idCliente = idClienteAsignado;
    }

    public void desasignarCliente() {
        this.idCliente = null;
    }

    public boolean tieneClienteAsignado() {
        return this.idCliente != null;
    }
}