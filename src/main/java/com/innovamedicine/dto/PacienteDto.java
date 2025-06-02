package com.innovamedicine.dto;

import com.innovamedicine.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class PacienteDto extends UsuarioDto {
    
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate fechaNacimiento;
    
    @NotBlank(message = "La talla es obligatoria")
    private String talla;
    
    @NotBlank(message = "El grupo sanguíneo es obligatorio")
    private String grupoSanguineo;
    
    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;
}