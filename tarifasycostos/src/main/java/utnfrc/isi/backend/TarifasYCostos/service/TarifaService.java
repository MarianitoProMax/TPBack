package utnfrc.isi.backend.TarifasYCostos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utnfrc.isi.backend.TarifasYCostos.dto.TarifaDTO;
import utnfrc.isi.backend.TarifasYCostos.entities.Tarifa;
import utnfrc.isi.backend.TarifasYCostos.repository.TarifaRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TarifaService {
    
    private final TarifaRepository tarifaRepository;
    
    /**
     * Crear nueva tarifa
     */
    public TarifaDTO crearTarifa(TarifaDTO dto) {
        Tarifa tarifa = new Tarifa();
        tarifa.setTipoCamion(dto.getTipoCamion());
        tarifa.setCostoKmBase(dto.getCostoKmBase());
        tarifa.setValorLitroCombustible(dto.getValorLitroCombustible());
        tarifa.setCargoGestionPorTramo(dto.getCargoGestionPorTramo());
        tarifa.setFechaVigenciaDesde(dto.getFechaVigenciaDesde());
        tarifa.setFechaVigenciaHasta(dto.getFechaVigenciaHasta());
        
        Tarifa guardada = tarifaRepository.save(tarifa);
        return mapToDTO(guardada);
    }
    
    /**
     * Obtener tarifa por ID
     */
    @Transactional(readOnly = true)
    public TarifaDTO obtenerTarifa(Long idTarifa) {
        Tarifa tarifa = tarifaRepository.findById(idTarifa)
            .orElseThrow(() -> new RuntimeException("Tarifa no encontrada: " + idTarifa));
        return mapToDTO(tarifa);
    }
    
    /**
     * Listar todas las tarifas
     */
    @Transactional(readOnly = true)
    public List<TarifaDTO> listarTodas() {
        return tarifaRepository.findAll()
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Listar tarifas por tipo de camión
     */
    @Transactional(readOnly = true)
    public List<TarifaDTO> listarPorTipoCamion(String tipoCamion) {
        return tarifaRepository.findByTipoCamion(tipoCamion)
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Listar tarifas vigentes en una fecha
     */
    @Transactional(readOnly = true)
    public List<TarifaDTO> listarTarifasVigentes(LocalDate fecha) {
        return tarifaRepository.findTarifasVigentes(fecha)
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Listar tarifas vigentes hoy
     */
    @Transactional(readOnly = true)
    public List<TarifaDTO> listarTarifasVigenteHoy() {
        return tarifaRepository.findTarifasVigentes(LocalDate.now())
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Listar tarifas vigentes por tipo de camión
     */
    @Transactional(readOnly = true)
    public List<TarifaDTO> listarTarifasVigentesPorTipo(String tipo) {
        return tarifaRepository.findTarifasVigentesByTipo(tipo)
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Asignar tarifa a un camión (microservicio externo)
     */
    public TarifaDTO asignarCamion(Long idTarifa, Long idCamion) {
        Tarifa tarifa = tarifaRepository.findById(idTarifa)
            .orElseThrow(() -> new RuntimeException("Tarifa no encontrada: " + idTarifa));
        
        if (tarifa.estaAsignadoACamion(idCamion)) {
            throw new RuntimeException("La tarifa ya está asignada a este camión");
        }
        
        tarifa.agregarCamion(idCamion);
        Tarifa actualizada = tarifaRepository.save(tarifa);
        
        return mapToDTO(actualizada);
    }
    
    /**
     * Desasignar tarifa de un camión
     */
    public TarifaDTO desasignarCamion(Long idTarifa, Long idCamion) {
        Tarifa tarifa = tarifaRepository.findById(idTarifa)
            .orElseThrow(() -> new RuntimeException("Tarifa no encontrada: " + idTarifa));
        
        if (!tarifa.estaAsignadoACamion(idCamion)) {
            throw new RuntimeException("La tarifa no está asignada a este camión");
        }
        
        tarifa.removerCamion(idCamion);
        Tarifa actualizada = tarifaRepository.save(tarifa);
        
        return mapToDTO(actualizada);
    }
    
    /**
     * Obtener todos los camiones asignados a una tarifa
     */
    @Transactional(readOnly = true)
    public List<Long> obtenerCamionesAsignados(Long idTarifa) {
        Tarifa tarifa = tarifaRepository.findById(idTarifa)
            .orElseThrow(() -> new RuntimeException("Tarifa no encontrada: " + idTarifa));
        
        return tarifa.getIdsCamiones();
    }
    
    /**
     * Verificar si una tarifa está asignada a un camión
     */
    @Transactional(readOnly = true)
    public Boolean estaAsignadaACamion(Long idTarifa, Long idCamion) {
        Tarifa tarifa = tarifaRepository.findById(idTarifa)
            .orElseThrow(() -> new RuntimeException("Tarifa no encontrada: " + idTarifa));
        
        return tarifa.estaAsignadoACamion(idCamion);
    }
    
    /**
     * Buscar tarifas en rango de costo
     */
    @Transactional(readOnly = true)
    public List<TarifaDTO> buscarPorRangoCosto(Double costoMin, Double costoMax) {
        return tarifaRepository.findByRangoCosto(costoMin, costoMax)
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Calcular costo de transporte
     * Fórmula: (costoKmBase × distancia) + (cargoGestionPorTramo)
     */
    @Transactional(readOnly = true)
    public Double calcularCostoTransporte(Long idTarifa, Double distancia) {
        Tarifa tarifa = tarifaRepository.findById(idTarifa)
            .orElseThrow(() -> new RuntimeException("Tarifa no encontrada: " + idTarifa));
        
        if (!estaVigente(tarifa)) {
            throw new RuntimeException("La tarifa no está vigente");
        }
        
        Double costoBase = (tarifa.getCostoKmBase() != null ? tarifa.getCostoKmBase() : 0) * (distancia != null ? distancia : 0);
        Double cargoGestion = tarifa.getCargoGestionPorTramo() != null ? tarifa.getCargoGestionPorTramo() : 0;
        
        return costoBase + cargoGestion;
    }
    
    /**
     * Verificar si una tarifa está vigente
     */
    private Boolean estaVigente(Tarifa tarifa) {
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(tarifa.getFechaVigenciaDesde()) &&
               (tarifa.getFechaVigenciaHasta() == null || !hoy.isAfter(tarifa.getFechaVigenciaHasta()));
    }
    
    /**
     * Mapear entidad a DTO
     */
    private TarifaDTO mapToDTO(Tarifa tarifa) {
        return TarifaDTO.builder()
            .idTarifa(tarifa.getIdTarifa())
            .tipoCamion(tarifa.getTipoCamion())
            .costoKmBase(tarifa.getCostoKmBase())
            .valorLitroCombustible(tarifa.getValorLitroCombustible())
            .cargoGestionPorTramo(tarifa.getCargoGestionPorTramo())
            .fechaVigenciaDesde(tarifa.getFechaVigenciaDesde())
            .fechaVigenciaHasta(tarifa.getFechaVigenciaHasta())
            .idsCamiones(tarifa.getIdsCamiones())
            .fechaCreacion(tarifa.getFechaCreacion())
            .fechaActualizacion(tarifa.getFechaActualizacion())
            .build();
    }
}
