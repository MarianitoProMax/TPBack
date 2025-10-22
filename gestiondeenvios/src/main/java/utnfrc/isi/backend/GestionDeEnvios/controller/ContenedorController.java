package utnfrc.isi.backend.GestionDeEnvios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utnfrc.isi.backend.GestionDeEnvios.dto.ContenedorDTO;
import utnfrc.isi.backend.GestionDeEnvios.service.ContenedorService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contenedores")
@CrossOrigin(origins = "*")
public class ContenedorController {
    
    @Autowired
    private ContenedorService contenedorService;
    
    @PostMapping
    public ResponseEntity<ContenedorDTO> crearContenedor(@RequestBody ContenedorDTO dto) {
        ContenedorDTO creado = contenedorService.crearContenedor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ContenedorDTO> obtenerContenedor(@PathVariable Long id) {
        ContenedorDTO contenedor = contenedorService.obtenerContenedor(id);
        return ResponseEntity.ok(contenedor);
    }
    
    @GetMapping("/identificacion/{identificacionUnica}")
    public ResponseEntity<ContenedorDTO> obtenerPorIdentificacion(@PathVariable String identificacionUnica) {
        ContenedorDTO contenedor = contenedorService.obtenerPorIdentificacion(identificacionUnica);
        return ResponseEntity.ok(contenedor);
    }
    
    @GetMapping
    public ResponseEntity<List<ContenedorDTO>> listarTodos() {
        List<ContenedorDTO> contenedores = contenedorService.listarTodos();
        return ResponseEntity.ok(contenedores);
    }
    
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ContenedorDTO>> listarPorEstado(@PathVariable String estado) {
        List<ContenedorDTO> contenedores = contenedorService.listarPorEstado(estado);
        return ResponseEntity.ok(contenedores);
    }
    
    @GetMapping("/buscar/peso")
    public ResponseEntity<List<ContenedorDTO>> buscarPorPeso(@RequestParam Double minPeso, @RequestParam Double maxPeso) {
        List<ContenedorDTO> contenedores = contenedorService.buscarPorPeso(minPeso, maxPeso);
        return ResponseEntity.ok(contenedores);
    }
    
    @GetMapping("/buscar/volumen")
    public ResponseEntity<List<ContenedorDTO>> buscarPorVolumen(@RequestParam Double minVolumen, @RequestParam Double maxVolumen) {
        List<ContenedorDTO> contenedores = contenedorService.buscarPorVolumen(minVolumen, maxVolumen);
        return ResponseEntity.ok(contenedores);
    }
    
    @PostMapping("/{idContenedor}/rutas/{idRuta}")
    public ResponseEntity<Map<String, String>> asignarRuta(@PathVariable Long idContenedor, @PathVariable Long idRuta) {
        contenedorService.asignarRuta(idContenedor, idRuta);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Ruta asignada al contenedor correctamente");
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{idContenedor}/rutas/{idRuta}")
    public ResponseEntity<Map<String, String>> desasignarRuta(@PathVariable Long idContenedor, @PathVariable Long idRuta) {
        contenedorService.desasignarRuta(idContenedor, idRuta);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Ruta desasignada del contenedor correctamente");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{idContenedor}/rutas")
    public ResponseEntity<List<Long>> obtenerRutasAsignadas(@PathVariable Long idContenedor) {
        List<Long> rutas = contenedorService.obtenerRutasAsignadas(idContenedor);
        return ResponseEntity.ok(rutas);
    }
    
    @GetMapping("/{idContenedor}/rutas/{idRuta}/asignada")
    public ResponseEntity<Map<String, Boolean>> estaRutaAsignada(@PathVariable Long idContenedor, @PathVariable Long idRuta) {
        boolean asignada = contenedorService.estaRutaAsignada(idContenedor, idRuta);
        Map<String, Boolean> response = new HashMap<>();
        response.put("asignada", asignada);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/ruta/{idRuta}/contenedores")
    public ResponseEntity<List<ContenedorDTO>> obtenerContenedoresPorRuta(@PathVariable Long idRuta) {
        List<ContenedorDTO> contenedores = contenedorService.obtenerContenedoresPorRuta(idRuta);
        return ResponseEntity.ok(contenedores);
    }
    
    @GetMapping("/con-rutas")
    public ResponseEntity<List<ContenedorDTO>> listarContenedoresConRutas() {
        List<ContenedorDTO> contenedores = contenedorService.listarContenedoresConRutas();
        return ResponseEntity.ok(contenedores);
    }
    
    @GetMapping("/estado/{estado}/con-rutas")
    public ResponseEntity<List<ContenedorDTO>> listarPorEstadoYConRutas(@PathVariable String estado) {
        List<ContenedorDTO> contenedores = contenedorService.listarPorEstadoYConRutas(estado);
        return ResponseEntity.ok(contenedores);
    }
    
    @PutMapping("/{idContenedor}/estado")
    public ResponseEntity<Map<String, String>> actualizarEstado(@PathVariable Long idContenedor, @RequestParam String nuevoEstado) {
        contenedorService.actualizarEstado(idContenedor, nuevoEstado);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Estado del contenedor actualizado correctamente");
        return ResponseEntity.ok(response);
    }
}
