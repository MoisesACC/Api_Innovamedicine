package com.innovamedicine.repository;

import com.innovamedicine.entity.Cita;
import com.innovamedicine.entity.Medico;
import com.innovamedicine.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByMedicoAndFechaHoraBetween(Medico medico, LocalDateTime start, LocalDateTime end);
    List<Cita> findByPaciente(Paciente paciente);
    boolean existsByMedicoAndFechaHora(Medico medico, LocalDateTime fechaHora);
}