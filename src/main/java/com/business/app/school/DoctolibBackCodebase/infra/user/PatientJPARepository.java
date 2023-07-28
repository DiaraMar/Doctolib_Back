package com.business.app.school.DoctolibBackCodebase.infra.user;

import com.business.app.school.DoctolibBackCodebase.domain.account.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PatientJPARepository extends JpaRepository<Patient, Long> {

    @Override
    Optional<Patient> findById(Long aLong);


    @Override
    <S extends Patient> S save(S entity);

    @Query(value="select * from doctolibdb.patient_doctolib a where a.user_id= ?1",  nativeQuery = true)
    Optional<Patient> findPatientAccountBy(@Param("id") Integer userID);
}
