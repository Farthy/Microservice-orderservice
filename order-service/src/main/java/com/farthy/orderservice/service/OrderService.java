package com.farthy.orderservice.service;

import com.farthy.orderservice.dto.OrderLineItemsDTO;
import com.farthy.orderservice.dto.OrderRequest;
import com.farthy.orderservice.model.Order;
import com.farthy.orderservice.model.OrderLineItems;
import com.farthy.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

       List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDTOList()
                .stream()
                .map(this::mapToDto)
                .toList();
       order.setOrderLineItemsList(orderLineItems);
       orderRepository.save(order);
        }

    private OrderLineItems mapToDto(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItems.getPrice());
        orderLineItems.setSkuCode(orderLineItems.getSkuCode());
        orderLineItems.setQuantity(orderLineItems.getQuantity());
        return  orderLineItems;
    }


}

