package com.hamzabekkaoui.customerservice.service.impl;

import com.hamzabekkaoui.customerservice.dto.request.CustomerRequest;
import com.hamzabekkaoui.customerservice.dto.response.CustomerResponse;
import com.hamzabekkaoui.customerservice.exception.ResourceAlreadyExistException;
import com.hamzabekkaoui.customerservice.exception.ResourceNotFoundException;
import com.hamzabekkaoui.customerservice.mapper.CustomerMapper;
import com.hamzabekkaoui.customerservice.model.Customer;
import com.hamzabekkaoui.customerservice.repository.CustomerRepository;
import com.hamzabekkaoui.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse create(CustomerRequest customerRequest) throws ResourceAlreadyExistException {

        boolean existsByMail = customerRepository.existsByMail(customerRequest.mail());
        boolean existsByUserName = customerRepository.existsByUserName(customerRequest.userName());
        if(existsByMail || existsByUserName) throw new ResourceAlreadyExistException("customer already exist");
        Customer customer = customerMapper.customerRequestToCustomer(customerRequest);
        Customer createdCustomer = customerRepository.save(customer);

        return customerMapper.customerToCustomerResponse(createdCustomer);
    }

    @Override
    public CustomerResponse update(Long id ,CustomerRequest customerRequest) throws ResourceNotFoundException {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("customer doesn't exist :-("));

        if(customerRequest.firstName() != null && !customerRequest.firstName().equals(customer.getFirstName()) )
            customer.setFirstName(customerRequest.firstName());
        if(customerRequest.lastName() != null && !customerRequest.lastName().equals(customer.getLastName()) )
            customer.setLastName(customerRequest.lastName());
        if(customerRequest.mail() != null && !customerRequest.mail().equals(customer.getMail()) )
            customer.setMail(customerRequest.mail());
        if(customerRequest.phoneNumber() != null && !customerRequest.phoneNumber().equals(customer.getPhoneNumber()) )
            customer.setPhoneNumber(customerRequest.phoneNumber());
        if(customerRequest.userName() != null && !customerRequest.userName().equals(customer.getUserName()) )
            customer.setUserName(customerRequest.userName());

        Customer updateCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerResponse(updateCustomer);
    }

    @Override
    public boolean delete(Long id) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("customer doesn't exist :-("));

        customerRepository.delete(customer);
        return true;
    }

    @Override
    public CustomerResponse getCustomerById(Long id) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("customer doesn't exist :-("));

        return customerMapper.customerToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse getCustomerByEmail(String email) throws ResourceNotFoundException {
        Customer customer = customerRepository.findByMail(email)
                .orElseThrow(() -> new ResourceNotFoundException("customer doesn't exist :-("));
        return customerMapper.customerToCustomerResponse(customer);
    }

    @Override
    public List<CustomerResponse> getListOfCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerResponse)
                .collect(Collectors.toList());

    }

    @Override
    public Page<CustomerResponse> getPageOfCustomers(int pageNumber, int pageSize) {
        Page<Customer> customers = customerRepository.findAll(PageRequest.of(pageNumber, pageSize));

        List<CustomerResponse> customerResponseList = customers.getContent()
                .stream()
                .map(customerMapper::customerToCustomerResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(customerResponseList , customers.getPageable() , customers.getTotalElements());
    }

    @Override
    public CustomerResponse getCustomerByUserName(String userName) throws ResourceNotFoundException {
        Customer customer = customerRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("customer doesn't exist"));
        return customerMapper.customerToCustomerResponse(customer);
    }


}
