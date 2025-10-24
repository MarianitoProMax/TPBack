package utnfrc.isi.backend.FlotaYDepositos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utnfrc.isi.backend.FlotaYDepositos.dto.CamionDTO;
import utnfrc.isi.backend.FlotaYDepositos.service.CamionService;
import java.util.List;

@RestController
@RequestMapping("/api/camiones")
@RequiredArgsConstructor
public class CamionController {
    
    private final CamionService camionService;
    
    /**
     * Crear nuevo camión
     */
    @PostMapping
    public ResponseEntity<CamionDTO> crear(@RequestBody CamionDTO dto) {
        CamionDTO creado = camionService.crearCamion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
    
    /**
     * Obtener camión por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<CamionDTO> obtener(@PathVariable Long id) {
        CamionDTO camion = camionService.obtenerCamion(id);
        return ResponseEntity.ok(camion);
    }
    
    /**
     * Obtener camión por dominio
     */
    @GetMapping("/dominio/{dominio}")
    public ResponseEntity<CamionDTO> obtenerPorDominio(@PathVariable String dominio) {
        CamionDTO camion = camionService.obtenerPorDominio(dominio);
        return ResponseEntity.ok(camion);
    }
    
    /**
     * Listar todos los camiones
     */
    @GetMapping
    public ResponseEntity<List<CamionDTO>> listar() {
        List<CamionDTO> camiones = camionService.listarTodos();
        return ResponseEntity.ok(camiones);
    }
    
    /**
     * Listar camiones disponibles
     */
    @GetMapping("/disponibles")
    public ResponseEntity<List<CamionDTO>> listarDisponibles() {
        List<CamionDTO> camiones = camionService.listarDisponibles();
        return ResponseEntity.ok(camiones);
    }
    
    /**
     * Buscar camiones por transportista
     */
    @GetMapping("/transportista/{nombre}")
    public ResponseEntity<List<CamionDTO>> buscarPorTransportista(@PathVariable String nombre) {
        List<CamionDTO> camiones = camionService.buscarPorTransportista(nombre);
        return ResponseEntity.ok(camiones);
    }
    
    /**
     * Buscar camiones con capacidad suficiente
     */
    @PostMapping("/buscar-capacidad")
    public ResponseEntity<List<CamionDTO>> buscarPorCapacidad(
        @RequestParam Double peso,
        @RequestParam Double volumen) {
        List<CamionDTO> camiones = camionService.buscarPorCapacidad(peso, volumen);
        return ResponseEntity.ok(camiones);
    }
    
    /**
     * Asignar camión a un tramo (del microservicio GestionDeEnvios)
     */
    @PostMapping("/{idCamion}/tramos/{idTramo}")
    public ResponseEntity<CamionDTO> asignarTramo(
        @PathVariable Long idCamion,
        @PathVariable Long idTramo) {
        CamionDTO actualizado = camionService.asignarTramo(idCamion, idTramo);
        return ResponseEntity.ok(actualizado);
    }
    
    /**
     * Desasignar camión de un tramo
     */
    @DeleteMapping("/{idCamion}/tramos/{idTramo}")
    public ResponseEntity<CamionDTO> desasignarTramo(
        @PathVariable Long idCamion,
        @PathVariable Long idTramo) {
        CamionDTO actualizado = camionService.desasignarTramo(idCamion, idTramo);
        return ResponseEntity.ok(actualizado);
    }
    
    /**
     * Obtener todos los tramos asignados a un camión
     */
    @GetMapping("/{idCamion}/tramos")
    public ResponseEntity<List<Long>> obtenerTramosAsignados(@PathVariable Long idCamion) {
        List<Long> tramos = camionService.obtenerTramosAsignados(idCamion);
        return ResponseEntity.ok(tramos);
    }
    
    /**
     * Verificar si un camión está asignado a un tramo
     */
    @GetMapping("/{idCamion}/tramos/{idTramo}/asignado")
    public ResponseEntity<Boolean> estaAsignadoATramo(
        @PathVariable Long idCamion,
        @PathVariable Long idTramo) {
        Boolean asignado = camionService.estaAsignadoATramo(idCamion, idTramo);
        return ResponseEntity.ok(asignado);
    }
    
    /**
     * Actualizar disponibilidad del camión
     */
    @PutMapping("/{id}/disponibilidad")
    public ResponseEntity<CamionDTO> actualizarDisponibilidad(
        @PathVariable Long id,
        @RequestParam Boolean disponible) {
        CamionDTO actualizado = camionService.actualizarDisponibilidad(id, disponible);
        return ResponseEntity.ok(actualizado);
    }
}
