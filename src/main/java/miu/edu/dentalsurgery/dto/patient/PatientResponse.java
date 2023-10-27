package miu.edu.dentalsurgery.dto.patient;

import miu.edu.dentalsurgery.dto.address.AddressResponse;

public record PatientResponse(Long patientId, String patNo, String lastName, String firstName,
                              AddressResponse address) {
}
