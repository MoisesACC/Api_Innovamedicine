package com.innovamedicine.controller;


import com.innovamedicine.dto.MedicoDto;
import com.innovamedicine.dto.PacienteDto;
import com.innovamedicine.dto.UsuarioDto;
import com.innovamedicine.entity.Usuario;
import com.innovamedicine.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @PostMapping("/medicos")
    public ResponseEntity<Usuario> registrarMedico(@Valid @RequestBody MedicoDto medicoDto) {
        Usuario usuario = usuarioService.registrarMedico(medicoDto);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }
    
    @PostMapping("/pacientes")
    public ResponseEntity<Usuario> registrarPaciente(@Valid @RequestBody PacienteDto pacienteDto) {
        Usuario usuario = usuarioService.registrarPaciente(pacienteDto);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }
}