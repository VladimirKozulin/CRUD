package com.example.rest.rest.web.controller.v2;

import com.example.rest.rest.mapper.v1.ClientMapper;
import com.example.rest.rest.mapper.v2.OrderMapperV2;
import com.example.rest.rest.model.Client;
import com.example.rest.rest.model.Order;
import com.example.rest.rest.service.OrderService;
import com.example.rest.rest.service.impl.DatabaseClientService;
import com.example.rest.rest.web.model.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/order")
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderService databaseOrderService;

    private final OrderMapperV2 orderMapper;

    private final ClientMapper clientMapper;

    private final DatabaseClientService databaseClientService;

    @GetMapping("/filter")
    public ResponseEntity<OrderListResponse> filterBy(@Valid OrderFilter filter) {
        return ResponseEntity.ok(
                orderMapper.orderListToOrderListResponse(databaseOrderService.filterBy(filter)));
    }

    @GetMapping
    public ResponseEntity<OrderListResponse> findAll() {
        return ResponseEntity.ok(
                orderMapper.orderListToOrderListResponse(
                        databaseOrderService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                orderMapper.orderToResponse(
                        databaseOrderService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody UpsertOrderRequest request) {
        Order newOrder = databaseOrderService.save(orderMapper.requestToOrder(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderMapper.orderToResponse(newOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable("id") Long orderId,
                                                @RequestBody @Valid UpsertOrderRequest request) {
        Order updatedOrder = databaseOrderService.update(orderMapper.requestToOrder(orderId,request));

        return ResponseEntity.ok(orderMapper.orderToResponse(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        databaseOrderService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/save-with-ordrs")
    public ResponseEntity<ClientResponse> createdWithOrders(@RequestBody CreateClientOrderRequest requests) { //Забили болт на мапперы
        Client client = Client.builder().name(requests.getName()).build();
        List<Order> orders = requests.getOrders()
                .stream()
                .map(orderRequest -> Order.builder()
                        .product(orderRequest.getProduct())
                        .cost(orderRequest.getCost())
                        .build())
                .toList();

        return ResponseEntity.status(HttpStatus.CREATED).body(
                clientMapper.clientToResponse(databaseClientService.saveWithOrders(client,orders)));
    }

}
