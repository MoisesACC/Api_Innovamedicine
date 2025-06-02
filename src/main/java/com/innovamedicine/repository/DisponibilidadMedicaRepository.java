package com.innovamedicine.repository;

import com.innovamedicine.entity.DisponibilidadMedica;
import com.innovamedicine.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisponibilidadMedicaRepository extends JpaRepository<DisponibilidadMedica, Long> {
    List<DisponibilidadMedica> findByMedico(Medico medico);
    void deleteByMedico(Medico medico);
}