package miu.edu.dentalsurgery.service;

import miu.edu.dentalsurgery.dto.address.AddressResponse2;
import miu.edu.dentalsurgery.exception.AddressNotFoundException;
import miu.edu.dentalsurgery.model.Address;

import java.util.List;

public interface AddressService {
    public List<AddressResponse2> getAllAddresses();
    public Address getAddressById(Long addressId) throws AddressNotFoundException;
    public Address updateAddress(Address address) throws AddressNotFoundException;
    public void deleteAddress(Address address) throws AddressNotFoundException;
    public void deleteAddressById(Long addressId) throws AddressNotFoundException;
    public Address addNewAddress(Address address);


}
