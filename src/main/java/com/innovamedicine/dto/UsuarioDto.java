package com.innovamedicine.dto;
import com.innovamedicine.entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.*;

@Getter @Setter
public class UsuarioDto {
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombre;
    
    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100, message = "El apellido no puede exceder los 100 caracteres")
    private String apellido;
    
    @NotNull(message = "El sexo es obligatorio")
    private Usuario.Sexo sexo;
    
    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20, message = "El teléfono no puede exceder los 20 caracteres")
    private String telefono;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
    private String email;
    
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 150, message = "La contraseña debe tener entre 8 y 150 caracteres")
    private String contrasenia;
    
    @NotNull(message = "El rol es obligatorio")
    private Usuario.Rol rol;
}