package com.hamzabekkaoui.customerservice.init;

import com.hamzabekkaoui.customerservice.dto.request.CustomerRequest;
import com.hamzabekkaoui.customerservice.dto.response.CustomerResponse;
import com.hamzabekkaoui.customerservice.repository.CustomerRepository;
import com.hamzabekkaoui.customerservice.service.impl.CustomerServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class createData {

    @Bean
    CommandLineRunner commandLineRunner(
            CustomerServiceImpl customerService,
            CustomerRepository customerRepository

    ){
        return args -> {


            CustomerRequest customerRequest = CustomerRequest.builder()
                    .firstName("Hamza")
                    .lastName("Bekkaoui")
                    .userName("HamzaBk")
                    .mail("hamza@gmail.com")
                    .city("Rabat")
                    .phoneNumber("0611111111")
                    .build();

            if(!customerRepository.existsByUserName("HamzaBk")){
                CustomerResponse customerResponse = customerService.create(customerRequest);
            }

        };
    }
}
