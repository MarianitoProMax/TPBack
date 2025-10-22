package utnfrc.isi.backend.Usuarios.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 100)
    private String apellido;
    
    @Column(nullable = false, unique = true, length = 150)
    private String email;
    
    @Column(length = 20)
    private String telefono;
    
    @Column(nullable = false, length = 50)
    private String rol;  // CLIENTE, OPERADOR, ADMINISTRADOR, TRANSPORTISTA
    
    @Column(name = "ids_solicitudes", columnDefinition = "jsonb")
    @Builder.Default
    private List<Long> idsSolicitudes = new ArrayList<>();
    
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    
    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
    
    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
    
    public void agregarSolicitud(Long idSolicitud) {
        if (idSolicitud != null && !this.idsSolicitudes.contains(idSolicitud)) {
            this.idsSolicitudes.add(idSolicitud);
        }
    }
    
    public void removerSolicitud(Long idSolicitud) {
        this.idsSolicitudes.remove(idSolicitud);
    }
    
    public boolean estaAsignadoASolicitud(Long idSolicitud) {
        return this.idsSolicitudes.contains(idSolicitud);
    }
}
