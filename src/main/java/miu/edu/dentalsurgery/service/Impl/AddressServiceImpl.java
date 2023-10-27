package miu.edu.dentalsurgery.service.Impl;

import miu.edu.dentalsurgery.dto.address.AddressResponse2;
import miu.edu.dentalsurgery.dto.patient.PatientResponse2;
import miu.edu.dentalsurgery.exception.AddressNotFoundException;
import miu.edu.dentalsurgery.model.Address;
import miu.edu.dentalsurgery.repository.AddressRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import miu.edu.dentalsurgery.service.AddressService;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;
    public AddressServiceImpl(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }
    @Override
    public List<AddressResponse2> getAllAddresses() {
        return addressRepository.findAll(Sort.by("city"))
                .stream()
                .map(a -> new AddressResponse2(
                        a.getAddressId(),
                        a.getState(),
                        a.getCity(),
                        a.getZipcode(),
                        new PatientResponse2(
                                a.getPatient().getPatientId(),
                                a.getPatient().getPatientNumber(),
                                a.getPatient().getFirstName(),
                                a.getPatient().getLastName()
                        )
                )).toList();
    }

    @Override
    public Address getAddressById(Long addressId) throws AddressNotFoundException {
        return addressRepository.findById(addressId)
                .orElse(null);
    }

    @Override
    public Address updateAddress(Address address) throws AddressNotFoundException {
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Address address) throws AddressNotFoundException{
        addressRepository.delete(address);

    }
    @Override
    public void deleteAddressById(Long addressId) throws AddressNotFoundException{
        addressRepository.deleteById(addressId);

    }

    @Override
    public Address addNewAddress(Address address) {
        return addressRepository.save(address);
    }

}
