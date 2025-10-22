package utnfrc.isi.backend.FlotaYDepositos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utnfrc.isi.backend.FlotaYDepositos.entities.Camion;
import java.util.List;
import java.util.Optional;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {
    
    // Buscar camión por dominio
    Optional<Camion> findByDominio(String dominio);
    
    // Buscar camiones disponibles
    List<Camion> findByDisponible(Boolean disponible);
    
    // Buscar camiones por nombre transportista
    @Query("SELECT c FROM Camion c WHERE LOWER(c.nombreTransportista) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Camion> buscarPorTransportista(@Param("nombre") String nombre);
    
    // Buscar camiones con capacidad suficiente
    @Query("SELECT c FROM Camion c WHERE c.capacidadPeso >= :peso AND c.capacidadVolumen >= :volumen AND c.disponible = true")
    List<Camion> findDisponiblesByCapacidad(@Param("peso") Double peso, @Param("volumen") Double volumen);
    
    // Buscar camiones asignados a un tramo específico (microservicio externo)
    @Query("SELECT c FROM Camion c WHERE :idTramo MEMBER OF c.idsTramos")
    Optional<Camion> findByIdTramo(@Param("idTramo") Long idTramo);
    
    // Buscar todos los camiones asignados a tramos
    @Query("SELECT c FROM Camion c WHERE SIZE(c.idsTramos) > 0")
    List<Camion> findCamionesConTramos();
    
    // Contar cuántos tramos tiene un camión
    @Query("SELECT SIZE(c.idsTramos) FROM Camion c WHERE c.idCamion = :idCamion")
    Integer countTramosByCamionId(@Param("idCamion") Long idCamion);
}
