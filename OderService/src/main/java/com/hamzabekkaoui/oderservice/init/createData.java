package com.hamzabekkaoui.oderservice.init;

import com.hamzabekkaoui.oderservice.dto.request.AddressRequest;
import com.hamzabekkaoui.oderservice.dto.request.CustomerApiRequest;
import com.hamzabekkaoui.oderservice.dto.request.OrderItemRequest;
import com.hamzabekkaoui.oderservice.dto.request.OrderRequest;
import com.hamzabekkaoui.oderservice.dto.response.CustomerResponse;
import com.hamzabekkaoui.oderservice.dto.response.OrderResponse;
import com.hamzabekkaoui.oderservice.service.OrderService;
import com.hamzabekkaoui.oderservice.service.impl.OrderServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class createData {


    @Bean
    CommandLineRunner commandLineRunner(OrderService orderService){
        return args -> {


            AddressRequest address = AddressRequest.builder()
                    .country("morocco")
                    .city("rabat")
                    .street("amal 4")
                    .postalCode("10000")
                    .build();

            OrderItemRequest pcHp = OrderItemRequest.builder()
                    .productName("laptop HP")
                    .quantity(10L)
                    .build();
            List<OrderItemRequest> orderItemRequests = new ArrayList<>();
            orderItemRequests.add(pcHp);


            OrderRequest orderRequest = OrderRequest.builder()
                    .userName("hamzaBK")
                    .addressRequest(address)
                    .orderItemRequests(orderItemRequests)
                    .build();


            OrderResponse orderResponse = orderService.create(orderRequest);


            System.out.println("==============================");
            System.out.println(orderResponse.id());
            System.out.println(orderResponse.orderCode());
            System.out.println(orderResponse.orderStatus());
            System.out.println(orderResponse.customerId());
            System.out.println(orderResponse.total());
            System.out.println("==============================");
        };
    }
}
