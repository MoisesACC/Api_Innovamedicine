package com.innovamedicine.service;

import com.innovamedicine.dto.PacienteDto;
import com.innovamedicine.entity.Paciente;
import com.innovamedicine.entity.Usuario;
import com.innovamedicine.repository.PacienteRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {
    
    private final PacienteRepository pacienteRepository;
    
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    
    @Transactional
    public Usuario registrarPaciente(Usuario usuario, PacienteDto pacienteDto) {
        Paciente paciente = new Paciente();
        paciente.setUsuario(usuario);
        paciente.setFechaNacimiento(pacienteDto.getFechaNacimiento());
        paciente.setTalla(pacienteDto.getTalla());
        paciente.setGrupoSanguineo(pacienteDto.getGrupoSanguineo());
        paciente.setDireccion(pacienteDto.getDireccion());
        
        pacienteRepository.save(paciente);
        return usuario;
    }
    
    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado"));
    }
}