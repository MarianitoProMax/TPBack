package utnfrc.isi.backend.FlotaYDepositos.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utnfrc.isi.backend.FlotaYDepositos.dto.CamionDTO;
import utnfrc.isi.backend.FlotaYDepositos.dto.CreateCamionRequestDTO;
import utnfrc.isi.backend.FlotaYDepositos.entities.Camion;
import utnfrc.isi.backend.FlotaYDepositos.repository.CamionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CamionServiceImpl implements CamionService {

    private final CamionRepository camionRepository;

    private CamionDTO convertToDto(Camion camion) {
        return new CamionDTO(
                camion.getIdCamion(),
                camion.getDominio(),
                camion.getNombreTransportista(),
                camion.getTelefono(),
                camion.getCapacidadPeso(),
                camion.getCapacidadVolumen(),
                camion.getDisponibilidad(),
                camion.getCostoPorKm()
        );
    }

    private Camion convertToEntity(CreateCamionRequestDTO dto) {
        return Camion.builder()
               .dominio(dto.dominio())
               .nombreTransportista(dto.nombreTransportista())
               .telefono(dto.telefono())
               .capacidadPeso(dto.capacidadPeso())
               .capacidadVolumen(dto.capacidadVolumen())
               .disponibilidad(dto.disponibilidad())
               .costoPorKm(dto.costoPorKm())
               .consumoCombustiblePorKm(dto.consumoCombustiblePorKm())
               .build();
    }

    @Override
    @Transactional
    public CamionDTO createCamion(CreateCamionRequestDTO requestDTO) {
        Camion camion = convertToEntity(requestDTO);
        camion.setFechaCreacion(LocalDateTime.now());
        camion.setFechaActualizacion(LocalDateTime.now());
        Camion savedCamion = camionRepository.save(camion);
        return convertToDto(savedCamion);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CamionDTO> getAllCamiones() {
        return camionRepository.findAll().stream()
               .map(this::convertToDto)
               .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CamionDTO> getCamionById(Long id) {
        return camionRepository.findById(id).map(this::convertToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CamionDTO> getCamionesByDisponibilidad(boolean disponible) {
        return camionRepository.findByDisponibilidad(disponible).stream()
               .map(this::convertToDto)
               .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CamionDTO updateCamion(Long id, CreateCamionRequestDTO requestDTO) {
        Camion camion = camionRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Camión no encontrado con id: " + id));

        camion.setDominio(requestDTO.dominio());
        camion.setNombreTransportista(requestDTO.nombreTransportista());
        camion.setTelefono(requestDTO.telefono());
        camion.setCapacidadPeso(requestDTO.capacidadPeso());
        camion.setCapacidadVolumen(requestDTO.capacidadVolumen());
        camion.setDisponibilidad(requestDTO.disponibilidad());
        camion.setCostoPorKm(requestDTO.costoPorKm());
        camion.setConsumoCombustiblePorKm(requestDTO.consumoCombustiblePorKm());
        camion.setFechaActualizacion(LocalDateTime.now());

        Camion updatedCamion = camionRepository.save(camion);
        return convertToDto(updatedCamion);
    }

    @Override
    @Transactional
    public void deleteCamion(Long id) {
        if (!camionRepository.existsById(id)) {
            throw new RuntimeException("Camión no encontrado con id: " + id);
        }
        camionRepository.deleteById(id);
    }
}