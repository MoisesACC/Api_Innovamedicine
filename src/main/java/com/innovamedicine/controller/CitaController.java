package com.innovamedicine.controller;

import com.innovamedicine.dto.CitaDto;
import com.innovamedicine.entity.Cita;
import com.innovamedicine.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {
    
    private final CitaService citaService;
    
    @Autowired
    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }
    
    @PostMapping
    public ResponseEntity<Cita> agendarCita(@Valid @RequestBody CitaDto citaDto) {
        Cita cita = citaService.agendarCita(citaDto);
        return new ResponseEntity<>(cita, HttpStatus.CREATED);
    }
    
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Cita>> listarCitasPorMedico(
            @PathVariable Long medicoId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        
        List<Cita> citas = citaService.listarCitasPorMedico(medicoId, inicio, fin);
        return ResponseEntity.ok(citas);
    }
    
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Cita>> listarCitasPorPaciente(@PathVariable Long pacienteId) {
        List<Cita> citas = citaService.listarCitasPorPaciente(pacienteId);
        return ResponseEntity.ok(citas);
    }
    
    @PatchMapping("/{citaId}/estado")
    public ResponseEntity<Cita> actualizarEstadoCita(
            @PathVariable Long citaId,
            @RequestParam String nuevoEstado) {
        
        Cita.EstadoCita estado = Cita.EstadoCita.valueOf(nuevoEstado);
        Cita cita = citaService.actualizarEstadoCita(citaId, estado);
        return ResponseEntity.ok(cita);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerCita(@PathVariable Long id) {
        Cita cita = citaService.buscarPorId(id);
        return ResponseEntity.ok(cita);
    }
}