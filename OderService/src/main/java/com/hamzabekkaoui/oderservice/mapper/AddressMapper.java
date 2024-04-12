package com.hamzabekkaoui.oderservice.mapper;

import com.hamzabekkaoui.oderservice.dto.request.AddressRequest;
import com.hamzabekkaoui.oderservice.dto.response.AddressResponse;
import com.hamzabekkaoui.oderservice.model.Address;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {


    public Address addressRequestToAddress(AddressRequest addressRequest){
        return Address.builder()
                .country(addressRequest.country())
                .city(addressRequest.city())
                .street(addressRequest.street())
                .postalCode(addressRequest.postalCode())
                .build();
    }

    public AddressResponse addressToAddressResponse(Address address){
        return AddressResponse.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .postalCode(address.getPostalCode())
                .build();
    }

}
