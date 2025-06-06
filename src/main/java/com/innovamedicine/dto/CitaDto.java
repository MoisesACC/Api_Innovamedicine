package com.innovamedicine.dto;

import com.innovamedicine.entity.Cita;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CitaDto {
    
    @NotNull(message = "El ID del médico es obligatorio")
    private Long medicoId;
    
    @NotNull(message = "El ID del paciente es obligatorio")
    private Long pacienteId;
    
    @NotNull(message = "La fecha y hora son obligatorias")
    @Future(message = "La cita debe ser en el futuro")
    private LocalDateTime fechaHora;
    
    @Size(max = 255, message = "El motivo no puede exceder los 255 caracteres")
    private String motivo;
    
    private Cita.EstadoCita estado;
}