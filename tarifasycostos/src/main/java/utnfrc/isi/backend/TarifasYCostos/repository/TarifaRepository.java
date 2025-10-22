package utnfrc.isi.backend.TarifasYCostos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utnfrc.isi.backend.TarifasYCostos.entities.Tarifa;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {
    
    // Buscar tarifa por tipo de camión
    List<Tarifa> findByTipoCamion(String tipoCamion);
    
    // Buscar tarifas vigentes en una fecha
    @Query("SELECT t FROM Tarifa t WHERE t.fechaVigenciaDesde <= :fecha AND (t.fechaVigenciaHasta IS NULL OR t.fechaVigenciaHasta >= :fecha)")
    List<Tarifa> findTarifasVigentes(@Param("fecha") LocalDate fecha);
    
    // Buscar tarifas vigentes por tipo de camión
    @Query("SELECT t FROM Tarifa t WHERE t.tipoCamion = :tipo AND t.fechaVigenciaDesde <= CURRENT_DATE AND (t.fechaVigenciaHasta IS NULL OR t.fechaVigenciaHasta >= CURRENT_DATE)")
    List<Tarifa> findTarifasVigentesByTipo(@Param("tipo") String tipo);
    
    // Buscar tarifa asignada a un camión específico
    @Query("SELECT t FROM Tarifa t WHERE :idCamion MEMBER OF t.idsCamiones")
    Optional<Tarifa> findByIdCamion(@Param("idCamion") Long idCamion);
    
    // Buscar todas las tarifas con camiones asignados
    @Query("SELECT t FROM Tarifa t WHERE SIZE(t.idsCamiones) > 0")
    List<Tarifa> findTarifasConCamiones();
    
    // Contar cuántos camiones tiene una tarifa
    @Query("SELECT SIZE(t.idsCamiones) FROM Tarifa t WHERE t.idTarifa = :idTarifa")
    Integer countCamionsByTarifaId(@Param("idTarifa") Long idTarifa);
    
    // Buscar tarifas en rango de costo
    @Query("SELECT t FROM Tarifa t WHERE t.costoKmBase BETWEEN :costoMin AND :costoMax ORDER BY t.costoKmBase ASC")
    List<Tarifa> findByRangoCosto(@Param("costoMin") Double costoMin, @Param("costoMax") Double costoMax);
}
