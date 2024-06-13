package com.E_Commerce_Application.E_Commerce.site.Application.Address;

import com.E_Commerce_Application.E_Commerce.site.Application.Model.Address;
import com.E_Commerce_Application.E_Commerce.site.Application.Model.User;
import com.E_Commerce_Application.E_Commerce.site.Application.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    public Address getAddress(Long id){
        Optional<Address> mayBeAddress = addressRepository.findById(id);
        return mayBeAddress.orElse(null);
    }

    public Optional<Address> getAddressById(Long id){
        return addressRepository.findById(id);
    }

    public void updateAddress(Long id,Address updatedAddress)throws Exception{
        Optional<Address> maybeAddress = addressRepository.findById(id);
        if(maybeAddress.isPresent()){
            Address oldAddress =maybeAddress.get();
            oldAddress.setStreet(updatedAddress.getStreet());
            oldAddress.setCity(updatedAddress.getCity());
            oldAddress.setState(updatedAddress.getState());
            oldAddress.setCountry(updatedAddress.getCountry());
            oldAddress.setPincode(updatedAddress.getPincode());
            addressRepository.save(oldAddress);
        }else {
            throw new Exception("Address Not Found");
        }
    }

    public Address saveAddress(Address address) throws Exception{
        User user = userRepository.findById(address.getUserId()).orElseThrow( () -> new Exception("User does not Exist"));
        address.setUser(user);
        return addressRepository.save(address);
    }

    public void deleteAddress(Long id){
        boolean addressExist = addressRepository.existsById(id);
        if(addressExist){
            addressRepository.deleteById(id);
        }
    }
}
