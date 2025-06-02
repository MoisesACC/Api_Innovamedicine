package com.innovamedicine.service;

import com.innovamedicine.dto.CitaDto;
import com.innovamedicine.entity.Cita;
import com.innovamedicine.entity.Medico;
import com.innovamedicine.entity.Paciente;
import com.innovamedicine.exception.ConflictException;
import com.innovamedicine.exception.*;
import com.innovamedicine.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaService {
    
    private final CitaRepository citaRepository;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;
    
    @Autowired
    public CitaService(CitaRepository citaRepository, MedicoService medicoService, 
                      PacienteService pacienteService) {
        this.citaRepository = citaRepository;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
    }
    
    @Transactional
    public Cita agendarCita(CitaDto citaDto) {
        Medico medico = medicoService.buscarPorId(citaDto.getMedicoId());
        Paciente paciente = pacienteService.buscarPorId(citaDto.getPacienteId());
        
        if (citaRepository.existsByMedicoAndFechaHora(medico, citaDto.getFechaHora())) {
            throw new ConflictException("El médico ya tiene una cita programada en ese horario");
        }
        
        Cita cita = new Cita();
        cita.setMedico(medico);
        cita.setPaciente(paciente);
        cita.setFechaHora(citaDto.getFechaHora());
        cita.setMotivo(citaDto.getMotivo());
        cita.setEstado(citaDto.getEstado() != null ? citaDto.getEstado() : Cita.EstadoCita.Pendiente);
        
        return citaRepository.save(cita);
    }
    
    public List<Cita> listarCitasPorMedico(Long medicoId, LocalDateTime inicio, LocalDateTime fin) {
        Medico medico = medicoService.buscarPorId(medicoId);
        return citaRepository.findByMedicoAndFechaHoraBetween(medico, inicio, fin);
    }
    
    public List<Cita> listarCitasPorPaciente(Long pacienteId) {
        Paciente paciente = pacienteService.buscarPorId(pacienteId);
        return citaRepository.findByPaciente(paciente);
    }
    
    @Transactional
    public Cita actualizarEstadoCita(Long citaId, Cita.EstadoCita nuevoEstado) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));
        
        cita.setEstado(nuevoEstado);
        return citaRepository.save(cita);
    }
    
    public Cita buscarPorId(Long id) {
        return citaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));
    }
}