package utnfrc.isi.backend.GestionDeEnvios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utnfrc.isi.backend.GestionDeEnvios.dto.RutaDTO;
import utnfrc.isi.backend.GestionDeEnvios.entities.Ruta;
import utnfrc.isi.backend.GestionDeEnvios.repository.RutaRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RutaService {
    
    @Autowired
    private RutaRepository rutaRepository;
    
    public RutaDTO crearRuta(RutaDTO dto) {
        Ruta ruta = Ruta.builder()
                .cantidadTramos(dto.getCantidadTramos() != null ? dto.getCantidadTramos() : 0)
                .cantidadDepositos(dto.getCantidadDepositos() != null ? dto.getCantidadDepositos() : 0)
                .build();
        
        Ruta guardada = rutaRepository.save(ruta);
        return convertirADTO(guardada);
    }
    
    public RutaDTO obtenerRuta(Long id) {
        Ruta ruta = rutaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con id: " + id));
        return convertirADTO(ruta);
    }
    
    public List<RutaDTO> listarTodas() {
        return rutaRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<RutaDTO> listarRutasConTramos() {
        return rutaRepository.findRutasConTramos().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<RutaDTO> listarRutasConDepositos() {
        return rutaRepository.findRutasConDepositos().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<RutaDTO> buscarPorRangoTramos(Integer minTramos, Integer maxTramos) {
        return rutaRepository.findByRangoTramos(minTramos, maxTramos).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<RutaDTO> buscarPorRangoDepositos(Integer minDepositos, Integer maxDepositos) {
        return rutaRepository.findByRangoDepositos(minDepositos, maxDepositos).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<RutaDTO> buscarPorCantidadTramos(Integer cantidad) {
        return rutaRepository.findByCantidadTramos(cantidad).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<RutaDTO> buscarPorCantidadDepositos(Integer cantidad) {
        return rutaRepository.findByCantidadDepositos(cantidad).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public List<RutaDTO> obtenerUltimosRegistros(Integer limit) {
        return rutaRepository.findUltimosRegistros(limit != null ? limit : 10).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    public void actualizarCantidadTramos(Long idRuta, Integer cantidadTramos) {
        Ruta ruta = rutaRepository.findById(idRuta)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con id: " + idRuta));
        
        ruta.setCantidadTramos(cantidadTramos);
        rutaRepository.save(ruta);
    }
    
    public void actualizarCantidadDepositos(Long idRuta, Integer cantidadDepositos) {
        Ruta ruta = rutaRepository.findById(idRuta)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con id: " + idRuta));
        
        ruta.setCantidadDepositos(cantidadDepositos);
        rutaRepository.save(ruta);
    }
    
    private RutaDTO convertirADTO(Ruta ruta) {
        return RutaDTO.builder()
                .idRuta(ruta.getIdRuta())
                .idSolicitud(ruta.getSolicitud() != null ? ruta.getSolicitud().getIdSolicitud() : null)
                .cantidadTramos(ruta.getCantidadTramos())
                .cantidadDepositos(ruta.getCantidadDepositos())
                .fechaCreacion(ruta.getFechaCreacion())
                .fechaActualizacion(ruta.getFechaActualizacion())
                .build();
    }
}
