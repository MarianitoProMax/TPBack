package utnfrc.isi.backend.Usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utnfrc.isi.backend.Usuarios.dto.request.ClienteCreateRequest;
import utnfrc.isi.backend.Usuarios.dto.request.ClienteUpdateRequest;
import utnfrc.isi.backend.Usuarios.dto.response.ClienteResponse;
import utnfrc.isi.backend.Usuarios.model.Cliente;
import utnfrc.isi.backend.Usuarios.repository.ClienteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    /**
     * Crear un nuevo cliente
     */
    public ClienteResponse crearCliente(ClienteCreateRequest request) {
        // Validar que el email no exista
        if (clienteRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        
        Cliente cliente = new Cliente();
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setEmail(request.getEmail());
        cliente.setTelefono(request.getTelefono());
        cliente.setRol(request.getRol());
        
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return mapToResponse(clienteGuardado);
    }
    
    /**
     * Obtener todos los clientes
     */
    @Transactional(readOnly = true)
    public List<ClienteResponse> obtenerTodosLosClientes() {
        return clienteRepository.findAll()
            .stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }
    
    /**
     * Obtener cliente por ID
     */
    @Transactional(readOnly = true)
    public ClienteResponse obtenerClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
        return mapToResponse(cliente);
    }
    
    /**
     * Actualizar cliente
     */
    public ClienteResponse actualizarCliente(Long id, ClienteUpdateRequest request) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
        
        // Validar que el nuevo email no esté en uso (si cambió)
        if (!cliente.getEmail().equals(request.getEmail())) {
            if (clienteRepository.findByEmail(request.getEmail()).isPresent()) {
                throw new IllegalArgumentException("El email ya está registrado");
            }
        }
        
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setEmail(request.getEmail());
        cliente.setTelefono(request.getTelefono());
        
        Cliente clienteActualizado = clienteRepository.save(cliente);
        return mapToResponse(clienteActualizado);
    }
    
    /**
     * Eliminar cliente
     */
    public void eliminarCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
        clienteRepository.delete(cliente);
    }
    
    /**
     * Buscar clientes con filtros
     */
    @Transactional(readOnly = true)
    public List<ClienteResponse> buscarClientes(String nombre, String email, String rol) {
        return clienteRepository.buscarClientes(nombre, email, rol)
            .stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }
    
    /**
     * Mapear entidad a DTO
     */
    private ClienteResponse mapToResponse(Cliente cliente) {
        return ClienteResponse.builder()
            .id(cliente.getId())
            .nombre(cliente.getNombre())
            .apellido(cliente.getApellido())
            .email(cliente.getEmail())
            .telefono(cliente.getTelefono())
            .rol(cliente.getRol())
            .fechaCreacion(cliente.getFechaCreacion())
            .fechaActualizacion(cliente.getFechaActualizacion())
            .build();
    }

    /**
     * Asignar solicitud a cliente (relación 1:N)
     */
    public void asignarSolicitud(Long idCliente, Long idSolicitud) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + idCliente));
        
        if (!cliente.estaAsignadoASolicitud(idSolicitud)) {
            cliente.agregarSolicitud(idSolicitud);
            clienteRepository.save(cliente);
        }
    }

    /**
     * Desasignar solicitud de cliente
     */
    public void desasignarSolicitud(Long idCliente, Long idSolicitud) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + idCliente));
        
        if (cliente.estaAsignadoASolicitud(idSolicitud)) {
            cliente.removerSolicitud(idSolicitud);
            clienteRepository.save(cliente);
        }
    }

    /**
     * Obtener todas las solicitudes asignadas a un cliente
     */
    @Transactional(readOnly = true)
    public List<Long> obtenerSolicitudesAsignadas(Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + idCliente));
        return cliente.getIdsSolicitudes();
    }

    /**
     * Verificar si una solicitud está asignada a un cliente
     */
    @Transactional(readOnly = true)
    public boolean estaSolicitudAsignada(Long idCliente, Long idSolicitud) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + idCliente));
        return cliente.estaAsignadoASolicitud(idSolicitud);
    }

    /**
     * Obtener todos los clientes que tienen una solicitud específica
     */
    @Transactional(readOnly = true)
    public List<ClienteResponse> obtenerClientesPorSolicitud(Long idSolicitud) {
        return clienteRepository.findByIdSolicitud(idSolicitud).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Listar todos los clientes que tienen al menos una solicitud asignada
     */
    @Transactional(readOnly = true)
    public List<ClienteResponse> listarClientesConSolicitudes() {
        return clienteRepository.findClientesConSolicitudes().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
}
