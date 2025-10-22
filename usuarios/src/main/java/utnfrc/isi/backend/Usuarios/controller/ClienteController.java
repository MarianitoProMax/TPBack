package utnfrc.isi.backend.Usuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utnfrc.isi.backend.Usuarios.dto.request.ClienteCreateRequest;
import utnfrc.isi.backend.Usuarios.dto.request.ClienteUpdateRequest;
import utnfrc.isi.backend.Usuarios.dto.response.ClienteResponse;
import utnfrc.isi.backend.Usuarios.service.ClienteService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    /**
     * POST /api/clientes
     * Crear un nuevo cliente
     */
    @PostMapping
    public ResponseEntity<ClienteResponse> crearCliente(@RequestBody ClienteCreateRequest request) {
        try {
            ClienteResponse response = clienteService.crearCliente(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    /**
     * GET /api/clientes
     * Obtener todos los clientes
     */
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> obtenerTodosLosClientes() {
        List<ClienteResponse> clientes = clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok(clientes);
    }
    
    /**
     * GET /api/clientes/{id}
     * Obtener un cliente por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> obtenerClientePorId(@PathVariable Long id) {
        try {
            ClienteResponse cliente = clienteService.obtenerClientePorId(id);
            return ResponseEntity.ok(cliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    /**
     * PUT /api/clientes/{id}
     * Actualizar un cliente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizarCliente(
            @PathVariable Long id,
            @RequestBody ClienteUpdateRequest request) {
        try {
            ClienteResponse response = clienteService.actualizarCliente(id, request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    /**
     * DELETE /api/clientes/{id}
     * Eliminar un cliente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        try {
            clienteService.eliminarCliente(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    /**
     * GET /api/clientes/buscar
     * Buscar clientes con filtros
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteResponse>> buscarClientes(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String rol) {
        List<ClienteResponse> clientes = clienteService.buscarClientes(nombre, email, rol);
        return ResponseEntity.ok(clientes);
    }

    /**
     * POST /api/clientes/{idCliente}/solicitudes/{idSolicitud}
     * Asignar una solicitud a un cliente (relación 1:N)
     */
    @PostMapping("/{idCliente}/solicitudes/{idSolicitud}")
    public ResponseEntity<Map<String, String>> asignarSolicitud(@PathVariable Long idCliente, @PathVariable Long idSolicitud) {
        try {
            clienteService.asignarSolicitud(idCliente, idSolicitud);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Solicitud asignada al cliente correctamente");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * DELETE /api/clientes/{idCliente}/solicitudes/{idSolicitud}
     * Desasignar una solicitud de un cliente
     */
    @DeleteMapping("/{idCliente}/solicitudes/{idSolicitud}")
    public ResponseEntity<Map<String, String>> desasignarSolicitud(@PathVariable Long idCliente, @PathVariable Long idSolicitud) {
        try {
            clienteService.desasignarSolicitud(idCliente, idSolicitud);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Solicitud desasignada del cliente correctamente");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * GET /api/clientes/{idCliente}/solicitudes
     * Obtener todas las solicitudes asignadas a un cliente
     */
    @GetMapping("/{idCliente}/solicitudes")
    public ResponseEntity<List<Long>> obtenerSolicitudesAsignadas(@PathVariable Long idCliente) {
        try {
            List<Long> solicitudes = clienteService.obtenerSolicitudesAsignadas(idCliente);
            return ResponseEntity.ok(solicitudes);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * GET /api/clientes/{idCliente}/solicitudes/{idSolicitud}/asignada
     * Verificar si una solicitud está asignada a un cliente
     */
    @GetMapping("/{idCliente}/solicitudes/{idSolicitud}/asignada")
    public ResponseEntity<Map<String, Boolean>> estaSolicitudAsignada(@PathVariable Long idCliente, @PathVariable Long idSolicitud) {
        try {
            boolean asignada = clienteService.estaSolicitudAsignada(idCliente, idSolicitud);
            Map<String, Boolean> response = new HashMap<>();
            response.put("asignada", asignada);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * GET /api/clientes/solicitud/{idSolicitud}/clientes
     * Obtener todos los clientes que tienen una solicitud específica
     */
    @GetMapping("/solicitud/{idSolicitud}/clientes")
    public ResponseEntity<List<ClienteResponse>> obtenerClientesPorSolicitud(@PathVariable Long idSolicitud) {
        List<ClienteResponse> clientes = clienteService.obtenerClientesPorSolicitud(idSolicitud);
        return ResponseEntity.ok(clientes);
    }

    /**
     * GET /api/clientes/con-solicitudes
     * Listar todos los clientes que tienen al menos una solicitud asignada
     */
    @GetMapping("/con-solicitudes")
    public ResponseEntity<List<ClienteResponse>> listarClientesConSolicitudes() {
        List<ClienteResponse> clientes = clienteService.listarClientesConSolicitudes();
        return ResponseEntity.ok(clientes);
    }
}
