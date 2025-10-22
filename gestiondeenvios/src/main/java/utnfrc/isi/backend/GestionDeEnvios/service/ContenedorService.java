package utnfrc.isi.backend.GestionDeEnvios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utnfrc.isi.backend.GestionDeEnvios.dto.ContenedorDTO;
import utnfrc.isi.backend.GestionDeEnvios.entities.Contenedor;
import utnfrc.isi.backend.GestionDeEnvios.repository.ContenedorRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContenedorService {
    
    @Autowired
    private ContenedorRepository contenedorRepository;
    
    public ContenedorDTO crearContenedor(ContenedorDTO dto) {
        Contenedor contenedor = Contenedor.builder()
                .identificacionUnica(dto.getIdentificacionUnica())
                .peso(dto.getPeso())
                .volumen(dto.getVolumen())
                .estado(dto.getEstado() != null ? dto.getEstado() : "DISPONIBLE")
                .build();
        
        Contenedor guardado = contenedorRepository.save(contenedor);
        return convertirADTO(guardado);
    }
    
    public ContenedorDTO obtenerContenedor(Long id) {
        Contenedor contenedor = contenedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contenedor no encontrado con id: " + id));
        return convertirADTO(contenedor);
    }
    
    public ContenedorDTO obtenerPorIdentificacion(String identificacionUnica) {
        Contenedor contenedor = contenedorRepository.findByIdentificacionUnica(identificacionUnica)
                .orElseThrow(() -> new RuntimeException("Contenedor no encontrado con identificación: " + identificacionUnica));
        return convertirADTO(contenedor);
    }
    
    public List<ContenedorDTO> listarTodos() {
        return contenedorRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ContenedorDTO> listarPorEstado(String estado) {
        return contenedorRepository.findByEstado(estado).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ContenedorDTO> buscarPorPeso(Double minPeso, Double maxPeso) {
        return contenedorRepository.findByPesoRange(minPeso, maxPeso).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ContenedorDTO> buscarPorVolumen(Double minVolumen, Double maxVolumen) {
        return contenedorRepository.findByVolumenRange(minVolumen, maxVolumen).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public void asignarRuta(Long idContenedor, Long idRuta) {
        Contenedor contenedor = contenedorRepository.findById(idContenedor)
                .orElseThrow(() -> new RuntimeException("Contenedor no encontrado con id: " + idContenedor));
        
        if (!contenedor.estaAsignadoARuta(idRuta)) {
            contenedor.agregarRuta(idRuta);
            contenedorRepository.save(contenedor);
        }
    }
    
    public void desasignarRuta(Long idContenedor, Long idRuta) {
        Contenedor contenedor = contenedorRepository.findById(idContenedor)
                .orElseThrow(() -> new RuntimeException("Contenedor no encontrado con id: " + idContenedor));
        
        if (contenedor.estaAsignadoARuta(idRuta)) {
            contenedor.removerRuta(idRuta);
            contenedorRepository.save(contenedor);
        }
    }
    
    public List<Long> obtenerRutasAsignadas(Long idContenedor) {
        Contenedor contenedor = contenedorRepository.findById(idContenedor)
                .orElseThrow(() -> new RuntimeException("Contenedor no encontrado con id: " + idContenedor));
        return contenedor.getIdsRutas();
    }
    
    public boolean estaRutaAsignada(Long idContenedor, Long idRuta) {
        Contenedor contenedor = contenedorRepository.findById(idContenedor)
                .orElseThrow(() -> new RuntimeException("Contenedor no encontrado con id: " + idContenedor));
        return contenedor.estaAsignadoARuta(idRuta);
    }
    
    public List<ContenedorDTO> obtenerContenedoresPorRuta(Long idRuta) {
        return contenedorRepository.findByIdRuta(idRuta).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ContenedorDTO> listarContenedoresConRutas() {
        return contenedorRepository.findContenedoresConRutas().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<ContenedorDTO> listarPorEstadoYConRutas(String estado) {
        return contenedorRepository.findByEstadoAndConRutas(estado).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public void actualizarEstado(Long idContenedor, String nuevoEstado) {
        Contenedor contenedor = contenedorRepository.findById(idContenedor)
                .orElseThrow(() -> new RuntimeException("Contenedor no encontrado con id: " + idContenedor));
        
        contenedor.setEstado(nuevoEstado);
        contenedorRepository.save(contenedor);
    }
    
    private ContenedorDTO convertirADTO(Contenedor contenedor) {
        return ContenedorDTO.builder()
                .idContenedor(contenedor.getIdContenedor())
                .identificacionUnica(contenedor.getIdentificacionUnica())
                .peso(contenedor.getPeso())
                .volumen(contenedor.getVolumen())
                .estado(contenedor.getEstado())
                .idsRutas(contenedor.getIdsRutas())
                .fechaCreacion(contenedor.getFechaCreacion())
                .fechaActualizacion(contenedor.getFechaActualizacion())
                .build();
    }
}
