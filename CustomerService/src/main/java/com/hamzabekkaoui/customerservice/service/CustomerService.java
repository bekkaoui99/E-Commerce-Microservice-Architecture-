package com.hamzabekkaoui.customerservice.service;

import com.hamzabekkaoui.customerservice.dto.request.CustomerRequest;
import com.hamzabekkaoui.customerservice.dto.response.CustomerResponse;
import com.hamzabekkaoui.customerservice.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.customerservice.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {

    CustomerResponse create(CustomerRequest customerRequest) throws ResourceAlreadyExistException;
    CustomerResponse update(Long id ,CustomerRequest customerRequest) throws ResourceNotFoundException;
    boolean delete(Long id) throws ResourceNotFoundException;
    CustomerResponse getCustomerById(Long id) throws ResourceNotFoundException;
    CustomerResponse getCustomerByEmail(String email) throws ResourceNotFoundException;
    List<CustomerResponse> getListOfCustomers();
    Page<CustomerResponse> getPageOfCustomers(int pageNumber, int pageSize);

    CustomerResponse getCustomerByUserName(String userName) throws ResourceNotFoundException;


}
