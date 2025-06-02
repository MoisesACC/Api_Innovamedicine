package com.innovamedicine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Entity
@Table(name = "DISPONIBILIDAD_MEDICA")
@Getter @Setter
public class DisponibilidadMedica {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DISPONIBILIDAD")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MEDICO", nullable = false)
    private Medico medico;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "DIA_SEMANA", nullable = false, columnDefinition = "ENUM('Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo')")
    private DiaSemana diaSemana;
    
    @Column(name = "HORA_INICIO", nullable = false)
    private Time horaInicio;
    
    @Column(name = "HORA_FIN", nullable = false)
    private Time horaFin;
    
    public enum DiaSemana {
        Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo
    }
}