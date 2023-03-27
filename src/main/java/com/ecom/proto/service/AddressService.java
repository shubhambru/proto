package com.ecom.proto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecom.proto.model.AddressDTO;
import com.ecom.proto.repository.AddressRepository;

@Service
public class AddressService {
    
    @Autowired
    AddressRepository repository;

    @Autowired
    AuthService authService;

    public ResponseEntity<?> getAllAddress(String token) {
        try {
            if(authService.validateToken(token)) {
                long uid = authService.getUidFromToken(token);
                return new ResponseEntity<List<AddressDTO>>(repository.findByUid(uid),HttpStatus.OK);
            }
            else {
                return new ResponseEntity<String>("Invalid Token",HttpStatus.UNAUTHORIZED);

            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Error fetching data"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> addAddress(String token, AddressDTO address) {
        try {
            if(authService.validateToken(token)) {
                long uid = authService.getUidFromToken(token);
                address.setUid(uid);
                if( address.getAddress()!=null && address.getCity()!=null && 
                    address.getCountry()!=null && address.getLandmark()!=null && 
                    address.getName()!=null && address.getState()!=null && 
                    address.getContactNumber()!=null && address.getType()!=null ){
                        repository.save(address);
                        return new ResponseEntity<String>("Address added",HttpStatus.OK);
                    }
                else{
                    return new ResponseEntity<String>("Incomplete data",HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return new ResponseEntity<String>("Invalid Token",HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Error fetching data"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteAddress(String token, long addressID) {
        try {
            if(authService.validateToken(token)) {
                long uid = authService.getUidFromToken(token);
                if(repository.existsById(addressID)){
                    Optional<AddressDTO> address = repository.findById(addressID);
                    if(!address.isEmpty() && address.get().getUid()==uid){
                        repository.deleteById(addressID);
                        return new ResponseEntity<String>("Deleted address",HttpStatus.OK);
                    }
                }
                return new ResponseEntity<String>("Invalid address id",HttpStatus.BAD_REQUEST);
            }
            else {
                return new ResponseEntity<String>("Invalid Token",HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>("Error fetching data"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
