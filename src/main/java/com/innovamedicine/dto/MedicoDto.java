package com.innovamedicine.dto;

import com.innovamedicine.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MedicoDto extends UsuarioDto {
    
    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;
    
    @NotBlank(message = "El número de colegiado es obligatorio")
    private String numeroColegiado;
    
    @NotBlank(message = "El código de hospital es obligatorio")
    private String codigoMedicoHospital;
}