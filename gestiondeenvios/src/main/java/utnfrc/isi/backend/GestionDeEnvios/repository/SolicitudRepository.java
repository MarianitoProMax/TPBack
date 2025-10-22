package utnfrc.isi.backend.GestionDeEnvios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utnfrc.isi.backend.GestionDeEnvios.entities.Solicitud;
import java.util.List;
import java.util.Optional;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    Optional<Solicitud> findByNumeroSolicitud(String numeroSolicitud);

    List<Solicitud> findByEstado(String estado);

    @Query("SELECT s FROM Solicitud s WHERE s.idCliente = :idCliente")
    List<Solicitud> findByIdCliente(@Param("idCliente") Long idCliente);

    @Query("SELECT s FROM Solicitud s WHERE s.idCliente IS NOT NULL")
    List<Solicitud> findSolicitudesConCliente();

    @Query("SELECT COUNT(s) FROM Solicitud s WHERE s.idCliente = :idCliente")
    Long countByIdCliente(@Param("idCliente") Long idCliente);

    @Query("SELECT s FROM Solicitud s WHERE s.estado = :estado AND s.idCliente = :idCliente")
    List<Solicitud> findByEstadoAndIdCliente(@Param("estado") String estado, @Param("idCliente") Long idCliente);

    @Query("SELECT s FROM Solicitud s WHERE s.costoEstimado BETWEEN :min AND :max ORDER BY s.costoEstimado")
    List<Solicitud> findByCostoEstimadoRange(@Param("min") Double min, @Param("max") Double max);
}
