package com.hamzabekkaoui.oderservice.service;

import com.hamzabekkaoui.oderservice.Exception.ResourceNotFoundException;
import com.hamzabekkaoui.oderservice.dto.request.CustomerApiRequest;
import com.hamzabekkaoui.oderservice.dto.request.OrderRequest;
import com.hamzabekkaoui.oderservice.dto.request.ProductApiRequest;
import com.hamzabekkaoui.oderservice.dto.response.CustomerResponse;
import com.hamzabekkaoui.oderservice.dto.response.OrderResponse;
import com.hamzabekkaoui.oderservice.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    OrderResponse create(OrderRequest orderRequest);
    OrderResponse update(String orderCode , OrderRequest orderRequest) throws ResourceNotFoundException;
    OrderResponse delete(String orderCode) throws ResourceNotFoundException;

    OrderResponse getOrderByOrderCode(String orderCode) throws ResourceNotFoundException;

    List<OrderResponse> getAllOrders();
    Page<OrderResponse> getAllOrders(int pageNumber , int pageSize);
    CustomerResponse getCustomerByUserName(CustomerApiRequest request) throws ResourceNotFoundException;
    ProductResponse getProductByUserName(ProductApiRequest request) throws ResourceNotFoundException;


}
