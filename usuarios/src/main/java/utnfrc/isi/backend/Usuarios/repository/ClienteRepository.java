package utnfrc.isi.backend.Usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utnfrc.isi.backend.Usuarios.model.Cliente;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    Optional<Cliente> findByEmail(String email);
    
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
    
    List<Cliente> findByRol(String rol);
    
    @Query("SELECT c FROM Cliente c WHERE " +
           "(:nombre IS NULL OR c.nombre LIKE %:nombre%) AND " +
           "(:email IS NULL OR c.email LIKE %:email%) AND " +
           "(:rol IS NULL OR c.rol = :rol)")
    List<Cliente> buscarClientes(
        @Param("nombre") String nombre,
        @Param("email") String email,
        @Param("rol") String rol
    );

    @Query("SELECT c FROM Cliente c WHERE :idSolicitud MEMBER OF c.idsSolicitudes")
    List<Cliente> findByIdSolicitud(@Param("idSolicitud") Long idSolicitud);

    @Query("SELECT c FROM Cliente c WHERE SIZE(c.idsSolicitudes) > 0")
    List<Cliente> findClientesConSolicitudes();

    @Query("SELECT c FROM Cliente c WHERE c.telefono IS NOT NULL AND c.rol = :rol")
    List<Cliente> findByRolWithPhoneNumber(@Param("rol") String rol);
}
