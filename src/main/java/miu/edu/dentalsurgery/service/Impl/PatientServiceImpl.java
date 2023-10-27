package miu.edu.dentalsurgery.service.Impl;
import miu.edu.dentalsurgery.dto.address.AddressResponse;
import miu.edu.dentalsurgery.dto.patient.PatientRequest;
import miu.edu.dentalsurgery.dto.patient.PatientResponse;
import miu.edu.dentalsurgery.exception.PatientNotFoundException;
import miu.edu.dentalsurgery.model.Address;
import miu.edu.dentalsurgery.model.Patient;
import miu.edu.dentalsurgery.repository.AddressRepository;
import miu.edu.dentalsurgery.repository.PatientRepository;
import miu.edu.dentalsurgery.service.PatientService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;
    private AddressRepository addressRepository;
    public PatientServiceImpl(PatientRepository patientRepository, AddressRepository addressRepository){

        this.patientRepository = patientRepository;
        this.addressRepository = addressRepository;
    }
    @Override
    public List<PatientResponse> getAllPatient() {

        return patientRepository.findAll(Sort.by("patientId"))
                .stream()
                //.sorted(Comparator.comparing(Patient::getLastName))
                .map(p -> new PatientResponse(
                        p.getPatientId(),
                        p.getPatientNumber(),
                        p.getFirstName(),
                        p.getLastName(),
                        new AddressResponse(
                                p.getAddress().getAddressId(),
                                p.getAddress().getState(),
                                p.getAddress().getCity(),
                                p.getAddress().getZipcode()
                        )
                )).toList();
    }

    @Override
    public PatientResponse getPatientById(Long patientId) throws PatientNotFoundException {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(String.format("error: Patient with %d, is not found", patientId)));
        return new PatientResponse(patient.getPatientId(), patient.getPatientNumber(),
                patient.getLastName(), patient.getFirstName(),
                new AddressResponse(
                        patient.getAddress().getAddressId(),
                        patient.getAddress().getState(),
                        patient.getAddress().getCity(),
                        patient.getAddress().getZipcode()
        ));
    }
    @Override
    public PatientResponse addNewPatient(PatientRequest patientRequest) {
        var newPatient = new Patient(null, patientRequest.patientNumber(), patientRequest.lastName(),
                patientRequest.firstName(),
                new Address(patientRequest.address().state(), patientRequest.address().city(),
                        patientRequest.address().zipcode()), null);
        var savedPatient = patientRepository.save(newPatient);
        PatientResponse patientResponse = new PatientResponse(savedPatient.getPatientId(),
                savedPatient.getPatientNumber(), savedPatient.getLastName(),
                savedPatient.getFirstName(), new AddressResponse(savedPatient.getAddress().getAddressId(),
                savedPatient.getAddress().getState(), savedPatient.getAddress().getCity(),
                savedPatient.getAddress().getZipcode()));
        return patientResponse;
    }

    @Override
    public List<Patient> searchPatient(String searchString) {
        return patientRepository.findPatientsByPatientNumberContainingOrLastNameContainingOrFirstNameContainingOrAddress_StateContainingOrAddress_CityContainingOrAddressZipcode(
                searchString, searchString, searchString, searchString, searchString, searchString
        );
    }

    @Override
    public PatientResponse updatePatient(Long patientId, PatientRequest patientRequest) throws PatientNotFoundException{
        Patient currentPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(String.format("error: Patient with %d, is not found", patientId)));
        if(currentPatient != null){
            currentPatient.setPatientNumber(patientRequest.patientNumber());
            currentPatient.setLastName(patientRequest.lastName());
            currentPatient.setFirstName(patientRequest.firstName());
            currentPatient.setAddress(new Address(patientRequest.address().state(),patientRequest.address().city(),
                    patientRequest.address().zipcode()));
            var updatedPatient = patientRepository.save(currentPatient);
            PatientResponse patientResponse = new PatientResponse(updatedPatient.getPatientId(),
                    updatedPatient.getPatientNumber(), updatedPatient.getLastName(),
                    updatedPatient.getFirstName(), new AddressResponse(updatedPatient.getAddress().getAddressId(),
                    updatedPatient.getAddress().getState(), updatedPatient.getAddress().getCity(),
                    updatedPatient.getAddress().getZipcode()));
            return patientResponse;

        }else{
            PatientResponse patientResponse = new PatientResponse(currentPatient.getPatientId(),
                    currentPatient.getPatientNumber(), currentPatient.getLastName(),
                    currentPatient.getFirstName(), new AddressResponse(currentPatient.getAddress().getAddressId(),
                    currentPatient.getAddress().getState(), currentPatient.getAddress().getCity(),
                    currentPatient.getAddress().getZipcode()));
            return patientResponse;
        }

    }

    @Override
    public void deletePatient(Long patientId) throws PatientNotFoundException{

        patientRepository.deleteById(patientId);
    }

    @Override
    public void deletePatientAddressById(Long patientId) throws PatientNotFoundException{
        var patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(String.format("error: Patient with %d, is not found", patientId)));
        var address = patient.getAddress();
        if(address != null){
            patient.setAddress(null);
            patientRepository.save(patient);
            addressRepository.deleteById(address.getAddressId());
        }

    }


}
