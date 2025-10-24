package utnfrc.isi.backend.FlotaYDepositos.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utnfrc.isi.backend.FlotaYDepositos.dto.CreateDepositoRequestDTO;
import utnfrc.isi.backend.FlotaYDepositos.dto.DepositoDTO;
import utnfrc.isi.backend.FlotaYDepositos.entities.Deposito;
import utnfrc.isi.backend.FlotaYDepositos.repository.DepositoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepositoServiceImpl implements DepositoService {

    private final DepositoRepository depositoRepository;

    private DepositoDTO convertToDto(Deposito deposito) {
        return new DepositoDTO(
                deposito.getIdDeposito(),
                deposito.getNombre(),
                deposito.getDireccion(),
                deposito.getLatitud(),
                deposito.getLongitud(),
                deposito.getCostoEstadiaDiario()
        );
    }

    private Deposito convertToEntity(CreateDepositoRequestDTO dto) {
        return Deposito.builder()
               .nombre(dto.nombre())
               .direccion(dto.direccion())
               .latitud(dto.latitud())
               .longitud(dto.longitud())
               .costoEstadiaDiario(dto.costoEstadiaDiario())
               .build();
    }

    @Override
    @Transactional
    public DepositoDTO createDeposito(CreateDepositoRequestDTO requestDTO) {
        Deposito deposito = convertToEntity(requestDTO);
        deposito.setFechaCreacion(LocalDateTime.now());
        deposito.setFechaActualizacion(LocalDateTime.now());
        Deposito savedDeposito = depositoRepository.save(deposito);
        return convertToDto(savedDeposito);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepositoDTO> getAllDepositos() {
        List<Deposito> depositos = depositoRepository.findAll();
        return depositos.stream()
               .map(this::convertToDto)
               .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DepositoDTO> getDepositoById(Long id) {
        return depositoRepository.findById(id).map(this::convertToDto);
    }

    @Override
    @Transactional
    public DepositoDTO updateDeposito(Long id, CreateDepositoRequestDTO requestDTO) {
        Deposito deposito = depositoRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Depósito no encontrado con id: " + id));

        deposito.setNombre(requestDTO.nombre());
        deposito.setDireccion(requestDTO.direccion());
        deposito.setLatitud(requestDTO.latitud());
        deposito.setLongitud(requestDTO.longitud());
        deposito.setCostoEstadiaDiario(requestDTO.costoEstadiaDiario());
        deposito.setFechaActualizacion(LocalDateTime.now());

        Deposito updatedDeposito = depositoRepository.save(deposito);
        return convertToDto(updatedDeposito);
    }

    @Override
    @Transactional
    public void deleteDeposito(Long id) {
        if (!depositoRepository.existsById(id)) {
            throw new RuntimeException("Depósito no encontrado con id: " + id);
        }
        depositoRepository.deleteById(id);
    }
}