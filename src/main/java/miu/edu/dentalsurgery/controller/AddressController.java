package miu.edu.dentalsurgery.controller;

import miu.edu.dentalsurgery.dto.address.AddressResponse2;
import miu.edu.dentalsurgery.model.Address;
import miu.edu.dentalsurgery.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/adsweb/api/v1/address")
public class AddressController {

    private AddressService addressService;
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }
    @GetMapping(value = "/list")
    public ResponseEntity<List<AddressResponse2>> getAllAddress(){

        return ResponseEntity.ok(addressService.getAllAddresses());
    }
    @PostMapping(value = "/new")
    public Address addNewAddress(Address address){

        return addressService.addNewAddress(address);
    }

}
