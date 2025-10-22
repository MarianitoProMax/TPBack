package utnfrc.isi.backend.GestionDeEnvios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utnfrc.isi.backend.GestionDeEnvios.dto.SolicitudDTO;
import utnfrc.isi.backend.GestionDeEnvios.entities.Solicitud;
import utnfrc.isi.backend.GestionDeEnvios.repository.SolicitudRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SolicitudService {
    
    @Autowired
    private SolicitudRepository solicitudRepository;
    
    public SolicitudDTO crearSolicitud(SolicitudDTO dto) {
        Solicitud solicitud = Solicitud.builder()
                .numeroSolicitud(dto.getNumeroSolicitud())
                .idCliente(dto.getIdCliente())
                .costoEstimado(dto.getCostoEstimado())
                .tiempoEstimado(dto.getTiempoEstimado())
                .estado(dto.getEstado() != null ? dto.getEstado() : "PENDIENTE")
                .build();
        
        Solicitud guardada = solicitudRepository.save(solicitud);
        return convertirADTO(guardada);
    }
    
    public SolicitudDTO obtenerSolicitud(Long id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con id: " + id));
        return convertirADTO(solicitud);
    }
    
    public SolicitudDTO obtenerPorNumero(String numeroSolicitud) {
        Solicitud solicitud = solicitudRepository.findByNumeroSolicitud(numeroSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con número: " + numeroSolicitud));
        return convertirADTO(solicitud);
    }
    
    public List<SolicitudDTO> listarTodas() {
        return solicitudRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<SolicitudDTO> listarPorEstado(String estado) {
        return solicitudRepository.findByEstado(estado).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<SolicitudDTO> obtenerSolicitudesPorCliente(Long idCliente) {
        return solicitudRepository.findByIdCliente(idCliente).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<SolicitudDTO> listarSolicitudesConCliente() {
        return solicitudRepository.findSolicitudesConCliente().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public Long contarSolicitudesPorCliente(Long idCliente) {
        return solicitudRepository.countByIdCliente(idCliente);
    }
    
    public List<SolicitudDTO> buscarPorEstadoYCliente(String estado, Long idCliente) {
        return solicitudRepository.findByEstadoAndIdCliente(estado, idCliente).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<SolicitudDTO> buscarPorRangoCosto(Double min, Double max) {
        return solicitudRepository.findByCostoEstimadoRange(min, max).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public void asignarCliente(Long idSolicitud, Long idCliente) {
        Solicitud solicitud = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con id: " + idSolicitud));
        
        solicitud.asignarCliente(idCliente);
        solicitudRepository.save(solicitud);
    }
    
    public void desasignarCliente(Long idSolicitud) {
        Solicitud solicitud = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con id: " + idSolicitud));
        
        solicitud.desasignarCliente();
        solicitudRepository.save(solicitud);
    }
    
    public boolean tieneClienteAsignado(Long idSolicitud) {
        Solicitud solicitud = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con id: " + idSolicitud));
        return solicitud.tieneClienteAsignado();
    }
    
    public void actualizarEstado(Long idSolicitud, String nuevoEstado) {
        Solicitud solicitud = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con id: " + idSolicitud));
        
        solicitud.setEstado(nuevoEstado);
        solicitudRepository.save(solicitud);
    }
    
    public void actualizarCostos(Long idSolicitud, Double costoFinal) {
        Solicitud solicitud = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con id: " + idSolicitud));
        
        solicitud.setCostoFinal(costoFinal);
        solicitudRepository.save(solicitud);
    }
    
    private SolicitudDTO convertirADTO(Solicitud solicitud) {
        return SolicitudDTO.builder()
                .idSolicitud(solicitud.getIdSolicitud())
                .numeroSolicitud(solicitud.getNumeroSolicitud())
                .idCliente(solicitud.getIdCliente())
                .idContenedor(solicitud.getContenedor() != null ? solicitud.getContenedor().getIdContenedor() : null)
                .costoEstimado(solicitud.getCostoEstimado())
                .tiempoEstimado(solicitud.getTiempoEstimado())
                .costoFinal(solicitud.getCostoFinal())
                .tiempoReal(solicitud.getTiempoReal())
                .estado(solicitud.getEstado())
                .fechaCreacion(solicitud.getFechaCreacion())
                .fechaActualizacion(solicitud.getFechaActualizacion())
                .build();
    }
}
