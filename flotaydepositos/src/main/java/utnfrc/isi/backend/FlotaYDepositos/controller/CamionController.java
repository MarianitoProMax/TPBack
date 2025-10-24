package utnfrc.isi.backend.FlotaYDepositos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utnfrc.isi.backend.FlotaYDepositos.dto.CamionDTO;
import utnfrc.isi.backend.FlotaYDepositos.dto.CreateCamionRequestDTO;
import utnfrc.isi.backend.FlotaYDepositos.services.CamionService;

import java.util.List;

@RestController
@RequestMapping("/api/camiones")
@RequiredArgsConstructor
public class CamionController {

    private final CamionService camionService;

    @PostMapping
    public ResponseEntity<CamionDTO> createCamion(@Valid @RequestBody CreateCamionRequestDTO requestDTO) {
        CamionDTO createdCamion = camionService.createCamion(requestDTO);
        return new ResponseEntity<>(createdCamion, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CamionDTO>> getCamiones(
            @RequestParam(required = false) Boolean disponible) {
        List<CamionDTO> camiones;
        if (disponible!= null) {
            camiones = camionService.getCamionesByDisponibilidad(disponible);
        } else {
            camiones = camionService.getAllCamiones();
        }
        return ResponseEntity.ok(camiones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CamionDTO> getCamionById(@PathVariable Long id) {
        return camionService.getCamionById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CamionDTO> updateCamion(@PathVariable Long id, @Valid @RequestBody CreateCamionRequestDTO requestDTO) {
        try {
            CamionDTO updatedCamion = camionService.updateCamion(id, requestDTO);
            return ResponseEntity.ok(updatedCamion);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCamion(@PathVariable Long id) {
        try {
            camionService.deleteCamion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}