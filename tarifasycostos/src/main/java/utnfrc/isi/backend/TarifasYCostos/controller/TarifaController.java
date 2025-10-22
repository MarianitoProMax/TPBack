package utnfrc.isi.backend.TarifasYCostos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utnfrc.isi.backend.TarifasYCostos.dto.TarifaDTO;
import utnfrc.isi.backend.TarifasYCostos.service.TarifaService;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tarifas")
@RequiredArgsConstructor
public class TarifaController {
    
    private final TarifaService tarifaService;
    
    /**
     * Crear nueva tarifa
     */
    @PostMapping
    public ResponseEntity<TarifaDTO> crear(@RequestBody TarifaDTO dto) {
        TarifaDTO creada = tarifaService.crearTarifa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }
    
    /**
     * Obtener tarifa por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<TarifaDTO> obtener(@PathVariable Long id) {
        TarifaDTO tarifa = tarifaService.obtenerTarifa(id);
        return ResponseEntity.ok(tarifa);
    }
    
    /**
     * Listar todas las tarifas
     */
    @GetMapping
    public ResponseEntity<List<TarifaDTO>> listar() {
        List<TarifaDTO> tarifas = tarifaService.listarTodas();
        return ResponseEntity.ok(tarifas);
    }
    
    /**
     * Listar tarifas por tipo de camión
     */
    @GetMapping("/tipo/{tipoCamion}")
    public ResponseEntity<List<TarifaDTO>> listarPorTipo(@PathVariable String tipoCamion) {
        List<TarifaDTO> tarifas = tarifaService.listarPorTipoCamion(tipoCamion);
        return ResponseEntity.ok(tarifas);
    }
    
    /**
     * Listar tarifas vigentes en una fecha específica
     */
    @GetMapping("/vigentes")
    public ResponseEntity<List<TarifaDTO>> listarTarifasVigentes(
        @RequestParam(required = false) LocalDate fecha) {
        List<TarifaDTO> tarifas;
        if (fecha != null) {
            tarifas = tarifaService.listarTarifasVigentes(fecha);
        } else {
            tarifas = tarifaService.listarTarifasVigenteHoy();
        }
        return ResponseEntity.ok(tarifas);
    }
    
    /**
     * Listar tarifas vigentes por tipo de camión
     */
    @GetMapping("/vigentes/tipo/{tipo}")
    public ResponseEntity<List<TarifaDTO>> listarTarifasVigentesPorTipo(@PathVariable String tipo) {
        List<TarifaDTO> tarifas = tarifaService.listarTarifasVigentesPorTipo(tipo);
        return ResponseEntity.ok(tarifas);
    }
    
    /**
     * Buscar tarifas en rango de costo
     */
    @GetMapping("/rango-costo")
    public ResponseEntity<List<TarifaDTO>> buscarPorRangoCosto(
        @RequestParam Double costoMin,
        @RequestParam Double costoMax) {
        List<TarifaDTO> tarifas = tarifaService.buscarPorRangoCosto(costoMin, costoMax);
        return ResponseEntity.ok(tarifas);
    }
    
    /**
     * Asignar tarifa a un camión (microservicio FlotaYDepositos)
     */
    @PostMapping("/{idTarifa}/camiones/{idCamion}")
    public ResponseEntity<TarifaDTO> asignarCamion(
        @PathVariable Long idTarifa,
        @PathVariable Long idCamion) {
        TarifaDTO actualizada = tarifaService.asignarCamion(idTarifa, idCamion);
        return ResponseEntity.ok(actualizada);
    }
    
    /**
     * Desasignar tarifa de un camión
     */
    @DeleteMapping("/{idTarifa}/camiones/{idCamion}")
    public ResponseEntity<TarifaDTO> desasignarCamion(
        @PathVariable Long idTarifa,
        @PathVariable Long idCamion) {
        TarifaDTO actualizada = tarifaService.desasignarCamion(idTarifa, idCamion);
        return ResponseEntity.ok(actualizada);
    }
    
    /**
     * Obtener todos los camiones asignados a una tarifa
     */
    @GetMapping("/{idTarifa}/camiones")
    public ResponseEntity<List<Long>> obtenerCamionesAsignados(@PathVariable Long idTarifa) {
        List<Long> camiones = tarifaService.obtenerCamionesAsignados(idTarifa);
        return ResponseEntity.ok(camiones);
    }
    
    /**
     * Verificar si una tarifa está asignada a un camión
     */
    @GetMapping("/{idTarifa}/camiones/{idCamion}/asignada")
    public ResponseEntity<Boolean> estaAsignadaACamion(
        @PathVariable Long idTarifa,
        @PathVariable Long idCamion) {
        Boolean asignada = tarifaService.estaAsignadaACamion(idTarifa, idCamion);
        return ResponseEntity.ok(asignada);
    }
    
    /**
     * Calcular costo de transporte
     * Fórmula: (costoKmBase × distancia) + cargoGestionPorTramo
     */
    @PostMapping("/{idTarifa}/calcular-costo")
    public ResponseEntity<Map<String, Double>> calcularCosto(
        @PathVariable Long idTarifa,
        @RequestParam Double distancia) {
        Double costo = tarifaService.calcularCostoTransporte(idTarifa, distancia);
        
        Map<String, Double> respuesta = new HashMap<>();
        respuesta.put("costoTotal", costo);
        
        return ResponseEntity.ok(respuesta);
    }
}
