package utnfrc.isi.backend.FlotaYDepositos.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utnfrc.isi.backend.FlotaYDepositos.dto.CreateDepositoRequestDTO;
import utnfrc.isi.backend.FlotaYDepositos.dto.DepositoDTO;
import utnfrc.isi.backend.FlotaYDepositos.services.DepositoService;

import java.util.List;

@RestController
@RequestMapping("/api/depositos")
@RequiredArgsConstructor
public class DepositoController {

    private final DepositoService depositoService;

    @PostMapping
    public ResponseEntity<DepositoDTO> createDeposito(@Valid @RequestBody CreateDepositoRequestDTO requestDTO) {
        DepositoDTO createdDeposito = depositoService.createDeposito(requestDTO);
        return new ResponseEntity<>(createdDeposito, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepositoDTO>> getAllDepositos() {
        List<DepositoDTO> depositos = depositoService.getAllDepositos();
        return ResponseEntity.ok(depositos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepositoDTO> getDepositoById(@PathVariable Long id) {
        return depositoService.getDepositoById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepositoDTO> updateDeposito(@PathVariable Long id, @Valid @RequestBody CreateDepositoRequestDTO requestDTO) {
        try {
            DepositoDTO updatedDeposito = depositoService.updateDeposito(id, requestDTO);
            return ResponseEntity.ok(updatedDeposito);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeposito(@PathVariable Long id) {
        try {
            depositoService.deleteDeposito(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}