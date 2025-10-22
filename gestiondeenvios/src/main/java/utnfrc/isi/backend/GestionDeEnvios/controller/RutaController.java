package utnfrc.isi.backend.GestionDeEnvios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utnfrc.isi.backend.GestionDeEnvios.dto.RutaDTO;
import utnfrc.isi.backend.GestionDeEnvios.service.RutaService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rutas")
@CrossOrigin(origins = "*")
public class RutaController {
    
    @Autowired
    private RutaService rutaService;
    
    @PostMapping
    public ResponseEntity<RutaDTO> crearRuta(@RequestBody RutaDTO dto) {
        RutaDTO creada = rutaService.crearRuta(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RutaDTO> obtenerRuta(@PathVariable Long id) {
        RutaDTO ruta = rutaService.obtenerRuta(id);
        return ResponseEntity.ok(ruta);
    }
    
    @GetMapping
    public ResponseEntity<List<RutaDTO>> listarTodas() {
        List<RutaDTO> rutas = rutaService.listarTodas();
        return ResponseEntity.ok(rutas);
    }
    
    @GetMapping("/con-tramos")
    public ResponseEntity<List<RutaDTO>> listarRutasConTramos() {
        List<RutaDTO> rutas = rutaService.listarRutasConTramos();
        return ResponseEntity.ok(rutas);
    }
    
    @GetMapping("/con-depositos")
    public ResponseEntity<List<RutaDTO>> listarRutasConDepositos() {
        List<RutaDTO> rutas = rutaService.listarRutasConDepositos();
        return ResponseEntity.ok(rutas);
    }
    
    @GetMapping("/buscar/rango-tramos")
    public ResponseEntity<List<RutaDTO>> buscarPorRangoTramos(@RequestParam Integer minTramos, @RequestParam Integer maxTramos) {
        List<RutaDTO> rutas = rutaService.buscarPorRangoTramos(minTramos, maxTramos);
        return ResponseEntity.ok(rutas);
    }
    
    @GetMapping("/buscar/rango-depositos")
    public ResponseEntity<List<RutaDTO>> buscarPorRangoDepositos(@RequestParam Integer minDepositos, @RequestParam Integer maxDepositos) {
        List<RutaDTO> rutas = rutaService.buscarPorRangoDepositos(minDepositos, maxDepositos);
        return ResponseEntity.ok(rutas);
    }
    
    @GetMapping("/cantidad-tramos/{cantidad}")
    public ResponseEntity<List<RutaDTO>> buscarPorCantidadTramos(@PathVariable Integer cantidad) {
        List<RutaDTO> rutas = rutaService.buscarPorCantidadTramos(cantidad);
        return ResponseEntity.ok(rutas);
    }
    
    @GetMapping("/cantidad-depositos/{cantidad}")
    public ResponseEntity<List<RutaDTO>> buscarPorCantidadDepositos(@PathVariable Integer cantidad) {
        List<RutaDTO> rutas = rutaService.buscarPorCantidadDepositos(cantidad);
        return ResponseEntity.ok(rutas);
    }
    
    @GetMapping("/ultimos/{limit}")
    public ResponseEntity<List<RutaDTO>> obtenerUltimosRegistros(@PathVariable Integer limit) {
        List<RutaDTO> rutas = rutaService.obtenerUltimosRegistros(limit);
        return ResponseEntity.ok(rutas);
    }
    
    @PutMapping("/{idRuta}/cantidad-tramos")
    public ResponseEntity<Map<String, String>> actualizarCantidadTramos(@PathVariable Long idRuta, @RequestParam Integer cantidadTramos) {
        rutaService.actualizarCantidadTramos(idRuta, cantidadTramos);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Cantidad de tramos actualizada correctamente");
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{idRuta}/cantidad-depositos")
    public ResponseEntity<Map<String, String>> actualizarCantidadDepositos(@PathVariable Long idRuta, @RequestParam Integer cantidadDepositos) {
        rutaService.actualizarCantidadDepositos(idRuta, cantidadDepositos);
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Cantidad de depósitos actualizada correctamente");
        return ResponseEntity.ok(response);
    }
}
