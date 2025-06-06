package com.innovamedicine.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "CITAS")
@Data
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CITAS")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MEDICO", nullable = false)
    private Medico medico;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PACIENTE", nullable = false)
    private Paciente paciente;
    
    @Column(name = "FECHA_HORA", nullable = false)
    private LocalDateTime fechaHora;
    
    @Column(name = "MOTIVO", length = 255)
    private String motivo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", columnDefinition = "ENUM('Pendiente', 'Confirmada', 'Cancelada', 'Finalizada') DEFAULT 'Pendiente'")
    private EstadoCita estado;
    
    public enum EstadoCita {
        Pendiente, Confirmada, Cancelada, Finalizada
    }
}