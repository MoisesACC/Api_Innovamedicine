package com.innovamedicine.service;

import com.innovamedicine.dto.MedicoDto;
import com.innovamedicine.entity.Medico;
import com.innovamedicine.entity.Usuario;
import com.innovamedicine.exception.DuplicateEntityException;
import com.innovamedicine.exception.EntityNotFoundException;
import com.innovamedicine.repository.MedicoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicoService {
    
    private final MedicoRepository medicoRepository;
    
    @Autowired
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }
    
    @Transactional
    public Usuario registrarMedico(Usuario usuario, MedicoDto medicoDto) {
    	 if (medicoRepository.existsByNumeroColegiado(medicoDto.getNumeroColegiado())) {
    	        throw new DuplicateEntityException("El número de colegiado ya está registrado");
    	    }
    	  if (medicoRepository.findByCodigoMedicoHospital(medicoDto.getCodigoMedicoHospital()).isPresent()) {
            throw new DuplicateEntityException("El código de hospital ya está registrado");
        }
        
        Medico medico = new Medico();
        medico.setUsuario(usuario);
        medico.setEspecialidad(medicoDto.getEspecialidad());
        medico.setNumeroColegiado(medicoDto.getNumeroColegiado());
        medico.setCodigoMedicoHospital(medicoDto.getCodigoMedicoHospital());
        
        medicoRepository.save(medico);
        return usuario;
    }
    
    public Medico buscarPorId(Long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Médico no encontrado"));
    }
    public List<Medico> buscarPorEspecialidad(String especialidad) {
        return medicoRepository.findByEspecialidad(especialidad);
    }
}