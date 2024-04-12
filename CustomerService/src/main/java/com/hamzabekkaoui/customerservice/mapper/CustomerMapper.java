package com.hamzabekkaoui.customerservice.mapper;

import com.hamzabekkaoui.customerservice.dto.request.CustomerRequest;
import com.hamzabekkaoui.customerservice.dto.response.CustomerResponse;
import com.hamzabekkaoui.customerservice.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer customerRequestToCustomer(CustomerRequest customerRequest){
        return Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .mail(customerRequest.mail())
                .phoneNumber(customerRequest.phoneNumber())
                .userName(customerRequest.userName())
                .build();
    }

    public CustomerResponse customerToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .mail(customer.getMail())
                .userName(customer.getUserName())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }

}
