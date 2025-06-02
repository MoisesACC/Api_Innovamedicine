package com.innovamedicine.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MEDICOS")
@Getter @Setter
@PrimaryKeyJoinColumn(name = "ID_MEDICO")
public class Medico extends Usuario {
    
    @Column(name = "ESPECIALIDAD", nullable = false, length = 100)
    private String especialidad;
    
    @Column(name = "NUMERO_COLEGIADO", nullable = false, length = 50)
    private String numeroColegiado;
    
    @Column(name = "CODIGO_MEDICO_HOSPITAL", nullable = false, unique = true, length = 100)
    private String codigoMedicoHospital;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "ID_MEDICO")
    private Usuario usuario;
}