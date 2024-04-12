package com.hamzabekkaoui.customerservice.controller;

import com.hamzabekkaoui.customerservice.dto.request.CustomerRequest;
import com.hamzabekkaoui.customerservice.dto.response.CustomerResponse;
import com.hamzabekkaoui.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerResponse create(@RequestBody CustomerRequest customerRequest){
        return customerService.create(customerRequest);
    }


    @PutMapping("/{id}")
    public CustomerResponse update(
            @PathVariable Long id,
            @RequestBody CustomerRequest customerRequest){
        return customerService.update(id,customerRequest);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return customerService.delete(id);
    }

    @GetMapping("/{id}")
    public CustomerResponse getOneCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping("/byMail")
    public CustomerResponse getOneCustomerByMail(@RequestBody Map<String , String> request){
        String mail = request.get("mail");
        return customerService.getCustomerByEmail(mail);
    }

    @GetMapping("/list")
    public List<CustomerResponse> getAllCustomers(){
        return customerService.getListOfCustomers();
    }

    @GetMapping("/page")
    public Page<CustomerResponse> getAllCustomers(
            @RequestParam(name = "pageNumber" , defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize" , defaultValue = "0") int pageSize
    ){
        return customerService.getPageOfCustomers(pageNumber , pageSize);
    }

    @PostMapping("/ByUserName")
    public CustomerResponse getOneCustomerByUserName(@RequestBody Map<String , String> request){
        String userName = request.get("userName");
        return customerService.getCustomerByUserName(userName);
    }





}
