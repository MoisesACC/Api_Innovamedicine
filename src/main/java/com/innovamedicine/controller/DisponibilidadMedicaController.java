package com.innovamedicine.controller;


import com.innovamedicine.dto.DisponibilidadMedicaDto;
import com.innovamedicine.entity.DisponibilidadMedica;
import com.innovamedicine.service.DisponibilidadMedicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disponibilidad")
public class DisponibilidadMedicaController {
    
    private final DisponibilidadMedicaService disponibilidadService;
    
    @Autowired
    public DisponibilidadMedicaController(DisponibilidadMedicaService disponibilidadService) {
        this.disponibilidadService = disponibilidadService;
    }
    
    @PostMapping
    public ResponseEntity<DisponibilidadMedica> agregarDisponibilidad(
            @Valid @RequestBody DisponibilidadMedicaDto dto) {
        
        DisponibilidadMedica disponibilidad = disponibilidadService.agregarDisponibilidad(dto);
        return new ResponseEntity<>(disponibilidad, HttpStatus.CREATED);
    }
    
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<DisponibilidadMedica>> listarDisponibilidadPorMedico(
            @PathVariable Long medicoId) {
        
        List<DisponibilidadMedica> disponibilidades = disponibilidadService.listarDisponibilidadPorMedico(medicoId);
        return ResponseEntity.ok(disponibilidades);
    }
    
    @PutMapping("/medico/{medicoId}")
    public ResponseEntity<Void> actualizarDisponibilidadMedico(
            @PathVariable Long medicoId,
            @RequestBody List<DisponibilidadMedicaDto> disponibilidades) {
        
        disponibilidadService.actualizarDisponibilidadMedico(medicoId, disponibilidades);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDisponibilidad(@PathVariable Long id) {
        disponibilidadService.eliminarDisponibilidad(id);
        return ResponseEntity.noContent().build();
    }
}