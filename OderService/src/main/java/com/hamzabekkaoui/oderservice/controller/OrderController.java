package com.hamzabekkaoui.oderservice.controller;

import com.hamzabekkaoui.oderservice.dto.request.OrderRequest;
import com.hamzabekkaoui.oderservice.dto.response.OrderResponse;
import com.hamzabekkaoui.oderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/order/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public OrderResponse create(@RequestBody OrderRequest orderRequest){
        return orderService.create(orderRequest);
    }

    @PutMapping("{orderCode}")
    public OrderResponse update(@PathVariable String orderCode ,@RequestBody OrderRequest orderRequest){
        return orderService.update(orderCode ,orderRequest);
    }

    @PostMapping("/byOrderCode")
    public OrderResponse getOrderByOrderCode(@RequestBody Map<String , String> request){
        String orderCode = request.get("orderCode");
        return orderService.getOrderByOrderCode(orderCode);
    }

    @DeleteMapping
    public OrderResponse delete(@RequestBody Map<String , String> request){
        String orderCode = request.get("orderCode");
        return orderService.delete(orderCode);
    }


    @PostMapping("/list")
    public List<OrderResponse> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping("/page")
    public Page<OrderResponse> getAllOrders(
            @RequestParam(name = "pageNumber" , defaultValue = "O") int pageNumber,
            @RequestParam(name = "pageSize" , defaultValue = "O") int pageSize
    ){
        return orderService.getAllOrders(pageNumber , pageSize);
    }




}
