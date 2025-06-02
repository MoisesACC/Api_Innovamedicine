package com.innovamedicine.repository;


import com.innovamedicine.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
	 List<Medico> findByEspecialidad(String especialidad);
	    List<Medico> findByEspecialidadIgnoreCase(String especialidad);
	    Optional<Medico> findByCodigoMedicoHospital(String codigoMedicoHospital);
	    boolean existsByCodigoMedicoHospital(String codigoMedicoHospital); // Nuevo método
	    boolean existsByNumeroColegiado(String numeroColegiado); // Nuevo método
    
}

