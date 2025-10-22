package utnfrc.isi.backend.GestionDeEnvios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utnfrc.isi.backend.GestionDeEnvios.entities.Ruta;
import java.util.List;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {

    @Query("SELECT r FROM Ruta r WHERE r.cantidadTramos > 0")
    List<Ruta> findRutasConTramos();

    @Query("SELECT r FROM Ruta r WHERE r.cantidadDepositos > 0")
    List<Ruta> findRutasConDepositos();

    @Query("SELECT r FROM Ruta r WHERE r.cantidadTramos BETWEEN :minTramos AND :maxTramos")
    List<Ruta> findByRangoTramos(@Param("minTramos") Integer minTramos, @Param("maxTramos") Integer maxTramos);

    @Query("SELECT r FROM Ruta r WHERE r.cantidadDepositos BETWEEN :minDepositos AND :maxDepositos")
    List<Ruta> findByRangoDepositos(@Param("minDepositos") Integer minDepositos, @Param("maxDepositos") Integer maxDepositos);

    @Query("SELECT r FROM Ruta r WHERE r.cantidadTramos = :cantidad")
    List<Ruta> findByCantidadTramos(@Param("cantidad") Integer cantidad);

    @Query("SELECT r FROM Ruta r WHERE r.cantidadDepositos = :cantidad")
    List<Ruta> findByCantidadDepositos(@Param("cantidad") Integer cantidad);

    @Query("SELECT r FROM Ruta r ORDER BY r.fechaCreacion DESC LIMIT :limit")
    List<Ruta> findUltimosRegistros(@Param("limit") Integer limit);
}
