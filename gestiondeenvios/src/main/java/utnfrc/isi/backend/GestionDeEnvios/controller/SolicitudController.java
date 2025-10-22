package utnfrc.isi.backend.GestionDeEnvios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utnfrc.isi.backend.GestionDeEnvios.dto.SolicitudDTO;
import utnfrc.isi.backend.GestionDeEnvios.service.SolicitudService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudController {
    
    @Autowired
    private SolicitudService solicitudService;
    
    @PostMapping
    public ResponseEntity<SolicitudDTO> crearSolicitud(@RequestBody SolicitudDTO dto) {
        SolicitudDTO creada = solicitudService.crearSolicitud(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> obtenerSolicitud(@PathVariable Long id) {
        SolicitudDTO solicitud = solicitudService.obtenerSolicitud(id);
        return ResponseEntity.ok(solicitud);
    }
    
    @GetMapping("/numero/{numero}")
    public ResponseEntity<SolicitudDTO> obtenerPorNumero(@PathVariable String numero) {
        SolicitudDTO solicitud = solicitudService.obtenerPorNumero(numero);
        return ResponseEntity.ok(solicitud);
    }
    
    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> listarTodas() {
        List<SolicitudDTO> solicitudes = solicitudService.listarTodas();
        return ResponseEntity.ok(solicitudes);
    }
    
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<SolicitudDTO>> listarPorEstado(@PathVariable String estado) {
        List<SolicitudDTO> solicitudes = solicitudService.listarPorEstado(estado);
        return ResponseEntity.ok(solicitudes);
    }
    
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<SolicitudDTO>> obtenerSolicitudesPorCliente(@PathVariable Long idCliente) {
        List<SolicitudDTO> solicitudes = solicitudService.obtenerSolicitudesPorCliente(idCliente);
        return ResponseEntity.ok(solicitudes);
    }
    
    @GetMapping("/con-cliente")
    public ResponseEntity<List<SolicitudDTO>> listarSolicitudesConCliente() {
        List<SolicitudDTO> solicitudes = solicitudService.listarSolicitudesConCliente();
        return ResponseEntity.ok(solicitudes);
    }
    
    @GetMapping("/cliente/{idCliente}/contar")
    public ResponseEntity<Long> contarSolicitudesPorCliente(@PathVariable Long idCliente) {
        Long cantidad = solicitudService.contarSolicitudesPorCliente(idCliente);
        return ResponseEntity.ok(cantidad);
    }
    
    @GetMapping("/buscar/cliente-estado")
    public ResponseEntity<List<SolicitudDTO>> buscarPorEstadoYCliente(@RequestParam String estado, @RequestParam Long idCliente) {
        List<SolicitudDTO> solicitudes = solicitudService.buscarPorEstadoYCliente(estado, idCliente);
        return ResponseEntity.ok(solicitudes);
    }
    
    @GetMapping("/buscar/rango-costo")
    public ResponseEntity<List<SolicitudDTO>> buscarPorRangoCosto(@RequestParam Double min, @RequestParam Double max) {
        List<SolicitudDTO> solicitudes = solicitudService.buscarPorRangoCosto(min, max);
        return ResponseEntity.ok(solicitudes);
    }
    
    @PostMapping("/{idSolicitud}/cliente/{idCliente}")
    public ResponseEntity<Map<String, String>> asignarCliente(@PathVariable Long idSolicitud, @PathVariable Long idCliente) {
        solicitudService.asignarCliente(idSolicitud, idCliente);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Cliente asignado a la solicitud correctamente");
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{idSolicitud}/cliente")
    public ResponseEntity<Map<String, String>> desasignarCliente(@PathVariable Long idSolicitud) {
        solicitudService.desasignarCliente(idSolicitud);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Cliente desasignado de la solicitud correctamente");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{idSolicitud}/cliente/asignado")
    public ResponseEntity<Map<String, Boolean>> tieneClienteAsignado(@PathVariable Long idSolicitud) {
        boolean tiene = solicitudService.tieneClienteAsignado(idSolicitud);
        Map<String, Boolean> response = new HashMap<>();
        response.put("tieneCliente", tiene);
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{idSolicitud}/estado")
    public ResponseEntity<Map<String, String>> actualizarEstado(@PathVariable Long idSolicitud, @RequestParam String nuevoEstado) {
        solicitudService.actualizarEstado(idSolicitud, nuevoEstado);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Estado de la solicitud actualizado correctamente");
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{idSolicitud}/costos")
    public ResponseEntity<Map<String, String>> actualizarCostos(@PathVariable Long idSolicitud, @RequestParam Double costoFinal) {
        solicitudService.actualizarCostos(idSolicitud, costoFinal);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Costos de la solicitud actualizados correctamente");
        return ResponseEntity.ok(response);
    }
}
