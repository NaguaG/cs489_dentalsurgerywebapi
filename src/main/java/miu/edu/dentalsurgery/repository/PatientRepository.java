package miu.edu.dentalsurgery.repository;
import miu.edu.dentalsurgery.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findPatientsByPatientNumberContainingOrLastNameContainingOrFirstNameContainingOrAddress_StateContainingOrAddress_CityContainingOrAddressZipcode(
            String patientNumber,  String lastName, String firstName, String state, String city, String zipcode);
}

//    SELECT p.*, a.* FROM cs489_apsd_dental_surgery_webapi.patients p
//        inner join cs489_apsd_dental_surgery_webapi.addresses a
//        on p.address_id = a.address_id
//        where p.pat_no like '%P100%'
//        OR p.last_name like '%Gillian%'
//        OR p.first_name like '%White%'
//        OR a.state like '%IA%'
//        OR a.city like '%Fairfield%'
//        OR a.zipcode like '%52556%'
