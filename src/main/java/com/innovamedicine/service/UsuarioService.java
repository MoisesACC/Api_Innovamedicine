package com.innovamedicine.service;

import com.innovamedicine.dto.MedicoDto;
import com.innovamedicine.dto.PacienteDto;
import com.innovamedicine.dto.UsuarioDto;
import com.innovamedicine.entity.Usuario;
import com.innovamedicine.exception.DuplicateEntityException;
import com.innovamedicine.exception.EntityNotFoundException;
import com.innovamedicine.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, MedicoService medicoService, 
                         PacienteService pacienteService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Transactional
    public Usuario registrarUsuario(UsuarioDto usuarioDto) {
        if (usuarioRepository.existsByEmail(usuarioDto.getEmail())) {
            throw new DuplicateEntityException("El email ya está registrado");
        }
        
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setSexo(usuarioDto.getSexo());
        usuario.setTelefono(usuarioDto.getTelefono());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setContrasenia(passwordEncoder.encode(usuarioDto.getContrasenia()));
        usuario.setRol(usuarioDto.getRol());
        
        return usuarioRepository.save(usuario);
    }
    
    @Transactional
    public Usuario registrarMedico(MedicoDto medicoDto) {
        Usuario usuario = registrarUsuario(medicoDto);
        return medicoService.registrarMedico(usuario, medicoDto);
    }
    
    @Transactional
    public Usuario registrarPaciente(PacienteDto pacienteDto) {
        Usuario usuario = registrarUsuario(pacienteDto);
        return pacienteService.registrarPaciente(usuario, pacienteDto);
    }
    
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }
    
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }
}