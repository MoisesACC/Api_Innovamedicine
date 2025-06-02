package com.innovamedicine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "USUARIOS")
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;
    
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "APELLIDO", nullable = false, length = 100)
    private String apellido;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "SEXO", nullable = false, columnDefinition = "ENUM('Masculino', 'Femenino', 'Otro')")
    private Sexo sexo;
    
    @Column(name = "TELEFONO", nullable = false, length = 20)
    private String telefono;
    
    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String email;
    
    @Column(name = "CONTRASENIA", nullable = false, length = 150)
    private String contrasenia;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ROL", nullable = false, columnDefinition = "ENUM('Paciente', 'Medico')")
    private Rol rol;
    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Medico medico;
    
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Paciente paciente;
    
    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
    private List<Cita> citasComoMedico;
    
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private List<Cita> citasComoPaciente;
    
    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
    private List<DisponibilidadMedica> disponibilidades;
    
    public enum Sexo {
        Masculino, Femenino, Otro
    }
    
    public enum Rol {
        Paciente, Medico
    }
}