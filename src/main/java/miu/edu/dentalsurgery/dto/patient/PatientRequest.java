package miu.edu.dentalsurgery.dto.patient;

import jakarta.validation.constraints.NotBlank;
import miu.edu.dentalsurgery.dto.address.AddressRequest;

public record PatientRequest(
        String patientNumber,
        @NotBlank(message = "Patient last name is required, not be null or empty !!!")
        String lastName,
        @NotBlank(message = "Patient first name is required, not be null or empty !!!")
        String firstName,

        AddressRequest address
) {
}
