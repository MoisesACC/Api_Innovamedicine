package com.innovamedicine.controller;

import com.innovamedicine.entity.Medico;
import com.innovamedicine.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {
    
    private final MedicoService medicoService;
    
    @Autowired
    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Medico> obtenerMedico(@PathVariable Long id) {
        Medico medico = medicoService.buscarPorId(id);
        return ResponseEntity.ok(medico);
    }
    
    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<Medico>> listarMedicosPorEspecialidad(@PathVariable String especialidad) {
        List<Medico> medicos = medicoService.buscarPorEspecialidad(especialidad);
        return ResponseEntity.ok(medicos);
    }
}