package miu.edu.dentalsurgery.dto.address;

public record AddressRequest(
        String state,
        String city,
        String zipcode
) {
}
