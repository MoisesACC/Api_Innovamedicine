package com.innovamedicine.service;

import com.innovamedicine.dto.DisponibilidadMedicaDto;
import com.innovamedicine.entity.DisponibilidadMedica;
import com.innovamedicine.entity.Medico;
import com.innovamedicine.exception.EntityNotFoundException;
import com.innovamedicine.repository.DisponibilidadMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DisponibilidadMedicaService {
    
    private final DisponibilidadMedicaRepository disponibilidadRepository;
    private final MedicoService medicoService;
    
    @Autowired
    public DisponibilidadMedicaService(DisponibilidadMedicaRepository disponibilidadRepository, 
                                     MedicoService medicoService) {
        this.disponibilidadRepository = disponibilidadRepository;
        this.medicoService = medicoService;
    }
    
    @Transactional
    public DisponibilidadMedica agregarDisponibilidad(DisponibilidadMedicaDto dto) {
        Medico medico = medicoService.buscarPorId(dto.getMedicoId());
        
        DisponibilidadMedica disponibilidad = new DisponibilidadMedica();
        disponibilidad.setMedico(medico);
        disponibilidad.setDiaSemana(dto.getDiaSemana());
        disponibilidad.setHoraInicio(dto.getHoraInicio());
        disponibilidad.setHoraFin(dto.getHoraFin());
        
        return disponibilidadRepository.save(disponibilidad);
    }
    
    public List<DisponibilidadMedica> listarDisponibilidadPorMedico(Long medicoId) {
        Medico medico = medicoService.buscarPorId(medicoId);
        return disponibilidadRepository.findByMedico(medico);
    }
    
    @Transactional
    public void eliminarDisponibilidad(Long id) {
        if (!disponibilidadRepository.existsById(id)) {
            throw new EntityNotFoundException("Disponibilidad no encontrada");
        }
        disponibilidadRepository.deleteById(id);
    }
    
    @Transactional
    public void actualizarDisponibilidadMedico(Long medicoId, List<DisponibilidadMedicaDto> disponibilidades) {
        Medico medico = medicoService.buscarPorId(medicoId);
        
        // Eliminar disponibilidades existentes
        disponibilidadRepository.deleteByMedico(medico);
        
        // Agregar las nuevas disponibilidades
        for (DisponibilidadMedicaDto dto : disponibilidades) {
            DisponibilidadMedica disponibilidad = new DisponibilidadMedica();
            disponibilidad.setMedico(medico);
            disponibilidad.setDiaSemana(dto.getDiaSemana());
            disponibilidad.setHoraInicio(dto.getHoraInicio());
            disponibilidad.setHoraFin(dto.getHoraFin());
            disponibilidadRepository.save(disponibilidad);
        }
    }
}