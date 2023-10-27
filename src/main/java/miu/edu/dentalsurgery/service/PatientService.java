package miu.edu.dentalsurgery.service;
import miu.edu.dentalsurgery.dto.patient.PatientRequest;
import miu.edu.dentalsurgery.dto.patient.PatientResponse;
import miu.edu.dentalsurgery.exception.PatientNotFoundException;
import miu.edu.dentalsurgery.model.Patient;

import java.util.List;

public interface PatientService {
    List<PatientResponse> getAllPatient();
    PatientResponse getPatientById(Long patientId) throws PatientNotFoundException;
    PatientResponse updatePatient(Long patientId, PatientRequest patientRequest) throws PatientNotFoundException;
    void deletePatient(Long patientId) throws PatientNotFoundException;
    void deletePatientAddressById(Long patientId) throws PatientNotFoundException;

    PatientResponse addNewPatient(PatientRequest patientRequest);
    List<Patient> searchPatient(String searchString);

}
