package com.hamzabekkaoui.oderservice.service.impl;

import com.hamzabekkaoui.oderservice.Exception.ResourceNotFoundException;
import com.hamzabekkaoui.oderservice.dto.request.CustomerApiRequest;
import com.hamzabekkaoui.oderservice.dto.request.OrderRequest;
import com.hamzabekkaoui.oderservice.dto.request.ProductApiRequest;
import com.hamzabekkaoui.oderservice.dto.response.*;
import com.hamzabekkaoui.oderservice.mapper.AddressMapper;
import com.hamzabekkaoui.oderservice.model.Address;
import com.hamzabekkaoui.oderservice.model.Order;
import com.hamzabekkaoui.oderservice.model.OrderItem;
import com.hamzabekkaoui.oderservice.model.OrderStatus;
import com.hamzabekkaoui.oderservice.repository.AddressRepository;
import com.hamzabekkaoui.oderservice.repository.OrderItemRepository;
import com.hamzabekkaoui.oderservice.repository.OrderRepository;
import com.hamzabekkaoui.oderservice.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final RestTemplate restTemplate;


    @Override
    public OrderResponse create(OrderRequest orderRequest) {
        Address address = addressMapper.addressRequestToAddress(orderRequest.addressRequest());
        Address createdAddress = addressRepository.save(address);

        CustomerResponse customerByUserName = getCustomerByUserName(CustomerApiRequest.builder()
                .userName(orderRequest.userName())
                .build());


        Order order = Order.builder()
                .orderCode(UUID.randomUUID().toString())
                .orderStatus(OrderStatus.PENDING)
                .address(createdAddress)
                .customerId(customerByUserName.id())
                .build();

        Order createdOrder = orderRepository.save(order);

        List<OrderItem> orderItem = orderRequest.orderItemRequests()
                .stream()
                .map(orderItemRequest -> {
                    ProductResponse productByUserName = getProductByName(ProductApiRequest.builder()
                            .title(orderItemRequest.productName())
                            .build());
                    return OrderItem.builder()
                            .order(createdOrder)
                            .productId(productByUserName.id())
                            .price(productByUserName.price())
                            .quantity(orderItemRequest.quantity())
                            .total(productByUserName.price() * orderItemRequest.quantity())
                            .build();
                }).collect(Collectors.toList());

        orderItemRepository.saveAll(orderItem);
        Double total= 0.0;
        for (OrderItem totalorderItem : orderItem){
            total += totalorderItem.getTotal();
        }
        createdOrder.setOrderItems(orderItem);
        System.out.println("total " + total);
        createdOrder.setTotal(total);


        return OrderResponse.builder()
                .id(createdOrder.getId())
                .customerId(createdOrder.getCustomerId())
                .orderCode(createdOrder.getOrderCode())
                .orderStatus(createdOrder.getOrderStatus())
                .total(createdOrder.getTotal())
                .build();
    }

    @Override
    public OrderResponse update(String orderCode, OrderRequest orderRequest) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public OrderResponse delete(String orderCode) throws ResourceNotFoundException {
        Order order = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new ResourceNotFoundException("order doesn't exist with order code : " + orderCode));

        orderRepository.delete(order);
        return OrderResponse.builder()
                .id(order.getId())
                .orderCode(order.getOrderCode())
                .orderStatus(order.getOrderStatus())
                .customerId(order.getCustomerId())
                .total(order.getTotal())
                .build();
    }

    @Override
    public OrderResponse getOrderByOrderCode(String orderCode) throws ResourceNotFoundException {
        Order order = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new ResourceNotFoundException("order doesn't exist with order code : " + orderCode));

        return OrderResponse.builder()
                .id(order.getId())
                .orderCode(order.getOrderCode())
                .orderStatus(order.getOrderStatus())
                .customerId(order.getCustomerId())
                .total(order.getTotal())
                .build();
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> OrderResponse.builder()
                        .id(order.getId())
                        .orderCode(order.getOrderCode())
                        .orderStatus(order.getOrderStatus())
                        .customerId(order.getCustomerId())
                        .total(order.getTotal())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public Page<OrderResponse> getAllOrders(int pageNumber, int pageSize) {
        Page<Order> orders = orderRepository.findAll(PageRequest.of(pageNumber, pageSize));

        List<OrderResponse> orderResponseList = orders.getContent()
                .stream()
                .map(order -> OrderResponse.builder()
                        .id(order.getId())
                        .orderCode(order.getOrderCode())
                        .orderStatus(order.getOrderStatus())
                        .customerId(order.getCustomerId())
                        .total(order.getTotal())
                        .build())
                .collect(Collectors.toList());

        return new PageImpl<>(orderResponseList , orders.getPageable() , orders.getTotalElements());
    }

    @Override
    public List<OrderDetailsResponse> getAllOrdersByCustomerName(String customerName) {

        CustomerResponse customerByUserName = getCustomerByUserName(CustomerApiRequest.builder()
                .userName(customerName)
                .build());
        List<Order> allOrders = orderRepository.findAllByCustomerId(customerByUserName.id());



        return  allOrders.stream()
                .map(order -> {
                    return OrderDetailsResponse.builder()
                            .id(order.getId())
                            .orderCode(order.getOrderCode())
                            .customerName(customerByUserName.userName())
                            .orderStatus(order.getOrderStatus())
                            .orderItemResponseList(order.getOrderItems().stream()
                                    .map(orderItem -> {
                                        return OrderItemResponse.builder()
                                                .id(orderItem.getId())
                                                .price(orderItem.getPrice())
                                                .quantity(orderItem.getQuantity())
                                                .total(orderItem.getTotal())
                                                .productId(orderItem.getProductId())
                                                .build();
                                    }).collect(Collectors.toList()))
                            .total(order.getTotal())
                            .build();
                }).collect(Collectors.toList());


    }

    @Override
    public Page<OrderDetailsResponse> getAllOrdersByCustomerName(String customerName, int pageNumber, int pageSize) {

        CustomerResponse customerByUserName = getCustomerByUserName(CustomerApiRequest.builder()
                .userName(customerName)
                .build());
        Page<Order> allOrders = orderRepository.findAllByCustomerId(customerByUserName.id(), PageRequest.of(pageNumber, pageSize));

        List<OrderDetailsResponse> orderDetailsResponseList = allOrders.getContent()
                .stream()
                .map(order -> {
                    return OrderDetailsResponse.builder()
                            .id(order.getId())
                            .orderCode(order.getOrderCode())
                            .customerName(customerByUserName.userName())
                            .orderStatus(order.getOrderStatus())
                            .orderItemResponseList(order.getOrderItems().stream()
                                    .map(orderItem -> {
                                        return OrderItemResponse.builder()
                                                .id(orderItem.getId())
                                                .price(orderItem.getPrice())
                                                .quantity(orderItem.getQuantity())
                                                .total(orderItem.getTotal())
                                                .productId(orderItem.getProductId())
                                                .build();
                                    }).collect(Collectors.toList()))
                            .total(order.getTotal())
                            .build();
                }).collect(Collectors.toList());

        return new PageImpl<>(orderDetailsResponseList , allOrders.getPageable() , allOrders.getTotalElements());
    }


    public CustomerResponse getCustomerByUserName(CustomerApiRequest request) throws ResourceNotFoundException{
        String url = "http://localhost:8081/api/v1/customers/ByUserName"; // Replace with your actual endpoint URL
        ResponseEntity<CustomerResponse> response = restTemplate.postForEntity(url, request, CustomerResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new ResourceNotFoundException("Customer not found");
        } else {
            throw new RuntimeException("Failed to get customer by username. Status code: " + response.getStatusCode());
        }

    }

    @Override
    public ProductResponse getProductByName(ProductApiRequest request) throws ResourceNotFoundException {
        String url = "http://localhost:8082/api/v1/products/ByName";
        ResponseEntity<ProductResponse> response = restTemplate.postForEntity(url, request, ProductResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new ResourceNotFoundException("Customer not found");
        } else {
            throw new RuntimeException("Failed to get customer by username. Status code: " + response.getStatusCode());
        }
    }

}
