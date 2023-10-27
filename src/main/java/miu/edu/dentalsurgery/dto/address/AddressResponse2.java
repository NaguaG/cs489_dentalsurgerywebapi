package miu.edu.dentalsurgery.dto.address;

import miu.edu.dentalsurgery.dto.patient.PatientResponse2;

public record AddressResponse2(Long addressId, String state, String city, String zipcode, PatientResponse2 patient) {
}
