package com.innovamedicine.dto;

import com.innovamedicine.entity.DisponibilidadMedica;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter @Setter
public class DisponibilidadMedicaDto {
    
    @NotNull(message = "El ID del médico es obligatorio")
    private Long medicoId;
    
    @NotNull(message = "El día de la semana es obligatorio")
    private DisponibilidadMedica.DiaSemana diaSemana;
    
    @NotNull(message = "La hora de inicio es obligatoria")
    private Time horaInicio;
    
    @NotNull(message = "La hora de fin es obligatoria")
    private Time horaFin;
}