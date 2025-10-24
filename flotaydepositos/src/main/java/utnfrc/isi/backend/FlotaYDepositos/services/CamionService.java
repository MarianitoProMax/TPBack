package utnfrc.isi.backend.FlotaYDepositos.services;

import utnfrc.isi.backend.FlotaYDepositos.dto.CamionDTO;
import utnfrc.isi.backend.FlotaYDepositos.dto.CreateCamionRequestDTO;

import java.util.List;
import java.util.Optional;

public interface CamionService {
    CamionDTO createCamion(CreateCamionRequestDTO requestDTO);
    List<CamionDTO> getAllCamiones();
    Optional<CamionDTO> getCamionById(Long id);
    List<CamionDTO> getCamionesByDisponibilidad(boolean disponible);
    CamionDTO updateCamion(Long id, CreateCamionRequestDTO requestDTO);
    void deleteCamion(Long id);
}