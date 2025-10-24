package utnfrc.isi.backend.FlotaYDepositos.services;

import utnfrc.isi.backend.FlotaYDepositos.dto.CreateDepositoRequestDTO;
import utnfrc.isi.backend.FlotaYDepositos.dto.DepositoDTO;

import java.util.List;
import java.util.Optional;

public interface DepositoService {
    DepositoDTO createDeposito(CreateDepositoRequestDTO requestDTO);
    List<DepositoDTO> getAllDepositos();
    Optional<DepositoDTO> getDepositoById(Long id);
    DepositoDTO updateDeposito(Long id, CreateDepositoRequestDTO requestDTO);
    void deleteDeposito(Long id);
}