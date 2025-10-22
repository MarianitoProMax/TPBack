package utnfrc.isi.backend.GestionDeEnvios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utnfrc.isi.backend.GestionDeEnvios.entities.Contenedor;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContenedorRepository extends JpaRepository<Contenedor, Long> {

    Optional<Contenedor> findByIdentificacionUnica(String identificacionUnica);

    List<Contenedor> findByEstado(String estado);

    @Query("SELECT c FROM Contenedor c WHERE c.peso BETWEEN :minPeso AND :maxPeso")
    List<Contenedor> findByPesoRange(@Param("minPeso") Double minPeso, @Param("maxPeso") Double maxPeso);

    @Query("SELECT c FROM Contenedor c WHERE c.volumen BETWEEN :minVolumen AND :maxVolumen")
    List<Contenedor> findByVolumenRange(@Param("minVolumen") Double minVolumen, @Param("maxVolumen") Double maxVolumen);

    @Query("SELECT c FROM Contenedor c WHERE :idRuta MEMBER OF c.idsRutas")
    List<Contenedor> findByIdRuta(@Param("idRuta") Long idRuta);

    @Query("SELECT c FROM Contenedor c WHERE SIZE(c.idsRutas) > 0")
    List<Contenedor> findContenedoresConRutas();

    @Query("SELECT c FROM Contenedor c WHERE c.estado = :estado AND SIZE(c.idsRutas) > 0")
    List<Contenedor> findByEstadoAndConRutas(@Param("estado") String estado);
}
