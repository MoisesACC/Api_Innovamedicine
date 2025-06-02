package com.innovamedicine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "PACIENTES")
@Getter @Setter
@PrimaryKeyJoinColumn(name = "ID_PACIENTE")
public class Paciente extends Usuario {
    
    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private LocalDate fechaNacimiento;
    
    @Column(name = "TALLA", nullable = false, length = 100)
    private String talla;
    
    @Column(name = "GRUPO_SANGUINEO", nullable = false, length = 5)
    private String grupoSanguineo;
    
    @Column(name = "DIRECCION", nullable = false, length = 255)
    private String direccion;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "ID_PACIENTE")
    private Usuario usuario;
}