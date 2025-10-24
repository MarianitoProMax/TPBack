package utnfrc.isi.backend.FlotaYDepositos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utnfrc.isi.backend.FlotaYDepositos.entities.Deposito;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {
}